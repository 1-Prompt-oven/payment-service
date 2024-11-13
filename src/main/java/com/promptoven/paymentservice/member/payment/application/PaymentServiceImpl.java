package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.common.domain.Payment;
import com.promptoven.paymentservice.common.domain.PaymentWay;
import com.promptoven.paymentservice.global.common.response.BaseResponseStatus;
import com.promptoven.paymentservice.global.error.BaseException;
import com.promptoven.paymentservice.member.payment.dto.out.KafkaMessageOutDto;
import com.promptoven.paymentservice.member.payment.dto.out.PaymentDetailResponseDto;
import com.promptoven.paymentservice.member.payment.infrastructure.PaymentRepository;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Message message;

    private final String secretKey = Dotenv.load().get("TOSS_SECRET_KEY");

    @Override
    public void test(String productUuid) {
        message.createPaymentMessage(KafkaMessageOutDto.toDto(productUuid));
    }

    @Override
    public void processPaymentCallback(String paymentKey, String orderId, Integer amount, String productUuid) {
        // Toss Payments API에서 결제 상세 정보 조회
        String url = "https://api.tosspayments.com/v1/payments/" + paymentKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(secretKey, "");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<PaymentDetailResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, PaymentDetailResponseDto.class);
        PaymentDetailResponseDto paymentDetails = response.getBody();

        if (paymentDetails == null) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_DATA);
        }

        // PaymentWay 변환
        PaymentWay paymentWay = PaymentWay.from(paymentDetails.getPaymentWay());

        // 결제 수단에 따라 methodId 설정
        String methodId = resolveMethodId(paymentWay, paymentDetails);

        // 결제 정보 저장
        Payment payment = Payment.builder()
                .methodId(methodId)
                .paymentWay(paymentWay)
                .approveNumber(paymentDetails.getApproveNumber())
                .amount(paymentDetails.getAmount())
                .build();

        paymentRepository.save(payment);

        message.createPaymentMessage(KafkaMessageOutDto.toDto(productUuid));
    }

    // 결제 수단에 따라 적절한 methodId를 반환하는 메서드
    private String resolveMethodId(PaymentWay paymentWay, PaymentDetailResponseDto paymentDetails) {
        return switch (paymentWay) {
            case CARD -> paymentDetails.getCardNumber(); // 카드 번호 (마스킹 처리됨)
            case CASH -> paymentDetails.getCashReceiptNumber(); // 현금영수증 번호
            case VIRTUAL_ACCOUNT -> paymentDetails.getVirtualAccountNumber(); // 가상계좌 번호
            case TRANSFER -> paymentDetails.getAccountNumber(); // 계좌 번호 (마스킹 처리됨)
            default -> "unknown"; // 기타 결제 수단의 경우 기본 값 설정
        };
    }
}
