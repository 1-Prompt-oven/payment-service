package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.in.PaymentCallbackRequestDto;
import com.promptoven.paymentservice.member.payment.dto.in.PaymentCookieRequestDto;

import java.util.List;

public interface PaymentService {

    void processPaymentCallback(PaymentCallbackRequestDto paymentCallbackRequestDto);

    void processCookiePaymentCallback(PaymentCookieRequestDto requestDto);

    void test(String memberUuid, List<String> productUuid);

    void testCookiePayment(String memberUuid, Integer cookieAmount);
}
