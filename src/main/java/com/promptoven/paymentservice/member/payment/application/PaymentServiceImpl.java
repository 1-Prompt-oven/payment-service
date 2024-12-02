package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.common.domain.Payment;
import com.promptoven.paymentservice.global.common.response.BaseResponse;
import com.promptoven.paymentservice.global.common.response.BaseResponseStatus;
import com.promptoven.paymentservice.global.error.BaseException;
import com.promptoven.paymentservice.member.payment.dto.in.PaymentCookieRequestDto;
import com.promptoven.paymentservice.member.payment.dto.in.PaymentProductRequestDto;
import com.promptoven.paymentservice.member.payment.dto.in.ProductResponseDto;
import com.promptoven.paymentservice.member.payment.dto.out.KafkaCookieMessageOutDto;
import com.promptoven.paymentservice.member.payment.dto.out.KafkaMessageOutDto;
import com.promptoven.paymentservice.member.payment.infrastructure.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ProductFeignClient productFeignClient;

    private final PaymentRepository paymentRepository;
    private final Message message;


    @Transactional
    @Override
    public void paymentProduct(PaymentProductRequestDto requestDto) {

        // productUuids를 순회하며 sellerUuid 매핑
        Map<String, String> productSellerMap = new HashMap<>();
        for (String productUuid : requestDto.getPurchaseList()) {
            // Feign Client를 통해 각 productUuid에 대해 Product 정보를 조회
            BaseResponse<ProductResponseDto> productDetails = productFeignClient.getProductByUuid(productUuid);
            if (productDetails == null) {
                throw new BaseException(BaseResponseStatus.NOT_FOUND_DATA);
            }
            productSellerMap.put(productUuid, productDetails.getResult().getSellerUuid());
        }

        Payment savedPayment = paymentRepository.save(PaymentProductRequestDto.toEntity(requestDto));

        // Kafka 메시지 생성 및 전송
        message.createPaymentMessage(
                KafkaMessageOutDto.toDto(
                        savedPayment.getPaymentId(),
                        requestDto.getMemberUuid(),
                        requestDto.getPurchaseList(),
                        productSellerMap // productUuid와 sellerUuid 매핑 정보 포함
                )
        );
    }

    @Transactional
    @Override
    public void paymentCookie(PaymentCookieRequestDto requestDto) {
        Payment savedPayment = paymentRepository.save(PaymentCookieRequestDto.toEntity(requestDto));

        // Kafka 메시지 생성 및 전송
        message.createCookiePaymentMessage(
                KafkaCookieMessageOutDto.toDto(
                        savedPayment.getPaymentId(),
                        requestDto.getMemberUuid(),
                        requestDto.getCookieAmount(),
                        requestDto.getApprovedAt()
                )
        );
    }

    //    @Transactional
    //    @Override
    //    public void test(String memberUuid, List<String> productUuids) {
    //        Long testPaymentId = 123L;
    //
    //        // productUuids를 순회하며 sellerUuid 매핑
    //        Map<String, String> productSellerMap = new HashMap<>();
    //        for (String productUuid : productUuids) {
    //            // Feign Client를 통해 각 productUuid에 대해 Product 정보를 조회
    //            BaseResponse<ProductResponseDto> productDetails = productFeignClient.getProductByUuid(productUuid);
    //            log.info("productDetails: {}", productDetails.getResult());
    //            if (productDetails == null) {
    //                throw new BaseException(BaseResponseStatus.NOT_FOUND_DATA);
    //            }
    //            productSellerMap.put(productUuid, productDetails.getResult().getSellerUuid());
    //        }
    //
    //        KafkaMessageOutDto kafkaMessageOutDto = KafkaMessageOutDto.toDto(testPaymentId, memberUuid, productUuids, productSellerMap);
    //        log.info("kafkaMessageOutDto: {}", kafkaMessageOutDto.getProductSellerMap());
    //
    //        message.createPaymentMessage(KafkaMessageOutDto.toDto(testPaymentId, memberUuid, productUuids, productSellerMap));
    //    }

    //    @Transactional
    //    @Override
    //    public void processPaymentCallback(PaymentCallbackRequestDto requestDto) {
    //
    //        String paymentKey = requestDto.getPaymentKey();
    //        List<String> productUuids = requestDto.getProductUuids();
    //        String memberUuid = requestDto.getMemberUuid();
    //
    //        // Toss Payments API에서 결제 상세 정보 조회
    //        String url = "https://api.tosspayments.com/v1/payments/" + paymentKey;
    //
    //        HttpHeaders headers = new HttpHeaders();
    //        headers.setBasicAuth(secretKey, "");
    //        HttpEntity<Void> entity = new HttpEntity<>(headers);
    //
    //        ResponseEntity<PaymentDetailResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, PaymentDetailResponseDto.class);
    //        PaymentDetailResponseDto paymentDetails = response.getBody();
    //
    //        if (paymentDetails == null) {
    //            throw new BaseException(BaseResponseStatus.NOT_FOUND_DATA);
    //        }
    //
    //        // PaymentWay 변환
    //        PaymentWay paymentWay = PaymentWay.from(paymentDetails.getPaymentWay());
    //
    //        // 결제 수단에 따라 methodId 설정
    //        String methodId = resolveMethodId(paymentWay, paymentDetails);
    //
    //        // productUuids를 순회하며 sellerUuid 매핑
    //        Map<String, String> productSellerMap = new HashMap<>();
    //        for (String productUuid : productUuids) {
    //            // Feign Client를 통해 각 productUuid에 대해 Product 정보를 조회
    //            BaseResponse<ProductResponseDto> productDetails = productFeignClient.getProductByUuid(productUuid);
    //            if (productDetails == null) {
    //                throw new BaseException(BaseResponseStatus.NOT_FOUND_DATA);
    //            }
    //            productSellerMap.put(productUuid, productDetails.getResult().getSellerUuid());
    //        }
    //
    //        // 결제 정보 저장
    //        Payment payment = Payment.builder()
    //                .memberUuid(memberUuid)
    //                .methodId(methodId)
    //                .paymentWay(paymentWay)
    //                .amount(paymentDetails.getAmount())
    //                .approveNumber(paymentDetails.getApproveNumber())
    //                .approvedAt(paymentDetails.getApprovedAt())
    //                .build();
    //
    //        Payment savedPayment = paymentRepository.save(payment);
    //
    //        // Kafka 메시지 생성 및 전송
    //        message.createPaymentMessage(
    //                KafkaMessageOutDto.toDto(
    //                        savedPayment.getPaymentId(),
    //                        memberUuid,
    //                        productUuids,
    //                        productSellerMap // productUuid와 sellerUuid 매핑 정보 포함
    //                )
    //        );
    //    }
    //
    //    @Transactional
    //    @Override
    //    public void testCookiePayment(String memberUuid, Integer cookieAmount) {
    //        String testPaymentKey = "test-payment-key"; // 테스트용 Payment Key
    //        String testApproveNumber = "test-approve-number"; // 테스트용 승인 번호
    //
    //        // 테스트용 결제 정보를 가정
    //        PaymentDetailResponseDto paymentDetails = PaymentDetailResponseDto.builder()
    //                .paymentKey(testPaymentKey)
    //                .paymentWay("CARD") // 테스트용 결제 방식 (예: CARD)
    //                .amount(cookieAmount) // 쿠키 충전 금액과 동일
    //                .approveNumber(testApproveNumber)
    //                .approvedAt(LocalDateTime.now()) // 현재 시간 기준 승인
    //                .build();
    //
    //        // PaymentWay 변환
    //        PaymentWay paymentWay = PaymentWay.from(paymentDetails.getPaymentWay());
    //
    //        // 결제 정보 저장
    //        Payment payment = Payment.builder()
    //                .memberUuid(memberUuid)
    //                .totalAmount(paymentDetails.getAmount())
    //                .methodId(testPaymentKey) // 테스트용 Payment Key 저장
    //                .paymentWay(paymentWay)
    //                .approveNumber(paymentDetails.getApproveNumber())
    //                .approvedAt(paymentDetails.getApprovedAt())
    //                .build();
    //
    //        Payment savedPayment = paymentRepository.save(payment);
    //
    //        // Kafka 메시지 생성 및 전송
    //        message.createCookiePaymentMessage(
    //                KafkaCookieMessageOutDto.toDto(
    //                        savedPayment.getPaymentId(),
    //                        memberUuid,
    //                        cookieAmount,
    //                        paymentDetails.getApprovedAt()
    //                )
    //        );
    //
    //        log.info("Test cookie payment processed: paymentId={}, memberUuid={}, cookieAmount={}",
    //                savedPayment.getPaymentId(), memberUuid, cookieAmount);
    //    }
    //
    //
    //    // 쿠키 결제 로직
    //    @Transactional
    //    @Override
    //    public void processCookiePaymentCallback(PaymentCookieRequestDto requestDto) {
    //
    //        String paymentKey = requestDto.getPaymentKey();
    //        String memberUuid = requestDto.getMemberUuid();
    //        Integer cookieAmount = requestDto.getCookieAmount();
    //
    //        // Toss Payments API에서 결제 상세 정보 조회
    //        String url = "https://api.tosspayments.com/v1/payments/" + paymentKey;
    //
    //        HttpHeaders headers = new HttpHeaders();
    //        headers.setBasicAuth(secretKey, "");
    //        HttpEntity<Void> entity = new HttpEntity<>(headers);
    //
    //        ResponseEntity<PaymentDetailResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, PaymentDetailResponseDto.class);
    //        PaymentDetailResponseDto paymentDetails = response.getBody();
    //
    //        if (paymentDetails == null) {
    //            throw new BaseException(BaseResponseStatus.NOT_FOUND_DATA);
    //        }
    //
    //        // PaymentWay 변환
    //        PaymentWay paymentWay = PaymentWay.from(paymentDetails.getPaymentWay());
    //
    //        // 결제 정보 저장
    //        Payment payment = Payment.builder()
    //                .memberUuid(memberUuid)
    //                .methodId(paymentKey) // Toss 결제 키를 저장
    //                .paymentWay(paymentWay)
    //                .amount(paymentDetails.getAmount())
    //                .approveNumber(paymentDetails.getApproveNumber())
    //                .approvedAt(paymentDetails.getApprovedAt())
    //                .build();
    //
    //        Payment savedPayment = paymentRepository.save(payment);
    //
    //        String paymentType = "CHARGE";
    //
    //        // Kafka 메시지 생성 및 전송
    //        message.createCookiePaymentMessage(
    //                KafkaCookieMessageOutDto.toDto(
    //                        savedPayment.getPaymentId(),
    //                        memberUuid,
    //                        cookieAmount,
    //                        paymentDetails.getApprovedAt()
    //                )
    //        );
    //    }
    //
    //    // 결제 수단에 따라 적절한 methodId를 반환하는 메서드
    //    private String resolveMethodId(PaymentWay paymentWay, PaymentDetailResponseDto paymentDetails) {
    //        return switch (paymentWay) {
    //            case CARD -> paymentDetails.getCardNumber(); // 카드 번호 (마스킹 처리됨)
    //            case CASH -> paymentDetails.getCashReceiptNumber(); // 현금영수증 번호
    //            case VIRTUAL_ACCOUNT -> paymentDetails.getVirtualAccountNumber(); // 가상계좌 번호
    //            case TRANSFER -> paymentDetails.getAccountNumber(); // 계좌 번호 (마스킹 처리됨)
    //            default -> "unknown"; // 기타 결제 수단의 경우 기본 값 설정
    //        };
    //    }
}
