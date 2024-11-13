package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.in.PaymentCallbackRequestDto;

public interface PaymentService {

    void processPaymentCallback(PaymentCallbackRequestDto paymentCallbackRequestDto);

    void test(String productUuid);
}
