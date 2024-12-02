package com.promptoven.paymentservice.member.payment.presentation;

import com.promptoven.paymentservice.global.common.response.BaseResponse;
import com.promptoven.paymentservice.member.payment.application.PaymentService;
import com.promptoven.paymentservice.member.payment.dto.in.PaymentCookieRequestDto;
import com.promptoven.paymentservice.member.payment.dto.in.PaymentProductRequestDto;
import com.promptoven.paymentservice.member.payment.dto.in.ProductResponseDto;
import com.promptoven.paymentservice.member.payment.vo.in.PaymentCookieRequestVo;
import com.promptoven.paymentservice.member.payment.vo.in.PaymentProductRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
@Tag(name = "결제 API", description = "결제 관련 API endpoints")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "상품 결제", description = "상품 결제")
    @PostMapping("/product")
    public BaseResponse<Void> paymentProduct(@RequestBody PaymentProductRequestVo requestVo) {

        paymentService.paymentProduct(PaymentProductRequestDto.toDto(requestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "쿠키 결제", description = "쿠키 결제")
    @PostMapping("/cookie")
    public BaseResponse<Void> paymentCookie(@RequestBody PaymentCookieRequestVo requestVo) {

        paymentService.paymentCookie(PaymentCookieRequestDto.toDto(requestVo));

        return new BaseResponse<>();
    }

    //    @Operation(summary = "상품 결제 콜백", description = "상품 결제 콜백")
    //    @PostMapping("/callback")
    //    public BaseResponse<Void> handlePaymentCallback(@RequestBody PaymentCallbackRequestVo requestVo) {
    //
    //        PaymentCallbackRequestDto requestDto = PaymentCallbackRequestDto.toDto(requestVo);
    //
    //        paymentService.processPaymentCallback(requestDto);
    //
    //        return new BaseResponse<>();
    //    }
    //
    //    @Operation(summary = "쿠키 결제 콜백", description = "쿠키 결제 콜백")
    //    @PostMapping("/cookie/callback")
    //    public BaseResponse<Void> handleCookiePaymentCallback(@RequestBody PaymentCookieRequestVo requestVo) {
    //
    //        paymentService.processCookiePaymentCallback(PaymentCookieRequestDto.toDto(requestVo));
    //
    //        return new BaseResponse<>();
    //
    //    }
    //
    //    @Operation(summary = "상품 결제 테스트", description = "상품 결제 테스트")
    //    @PostMapping("/test")
    //    public BaseResponse<Void> test(@RequestParam String memberUuid,
    //                                   @RequestParam List<String> productUuid) {
    //        paymentService.test(memberUuid, productUuid);
    //        return new BaseResponse<>();
    //    }
    //
    //    @Operation(summary = "쿠키 결제 테스트", description = "쿠키 결제 테스트")
    //    @PostMapping("/cookie/test")
    //    public BaseResponse<Void> testCookiePayment(@RequestParam String memberUuid,
    //                                                @RequestParam Integer cookieAmount) {
    //        paymentService.testCookiePayment(memberUuid, cookieAmount);
    //        return new BaseResponse<>();
    //    }

    @Operation(summary = "Feign Client 테스트 API", description = "Feign Client 테스트 API")
    @GetMapping("/product/seller/test/{productUUID}")
    public BaseResponse<ProductResponseDto> getProductByUuid(@PathVariable("productUUID") String productUUID) {
        log.info("productUUID by test: {}", productUUID);
        ProductResponseDto productResponseDto = new ProductResponseDto("String");
        log.info("productResponseDto: {}", productResponseDto.getSellerUuid());
        return new BaseResponse<>(productResponseDto);
    }
}
