package com.promptoven.paymentservice.member.payment.presentation;

import com.promptoven.paymentservice.global.common.response.BaseResponse;
import com.promptoven.paymentservice.member.payment.application.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/callback")
    public BaseResponse<Void> handlePaymentCallback(
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam Integer amount,
            @RequestParam String productUuid) {

        // 결제 상세 정보를 조회하고, 결제 내역 저장 로직 실행
        paymentService.processPaymentCallback(paymentKey, orderId, amount, productUuid);

        return new BaseResponse<>();
    }

    @PostMapping("/test")
    public BaseResponse<Void> test(@RequestParam String productUuid) {
        paymentService.test(productUuid);
        return new BaseResponse<>();
    }
}
