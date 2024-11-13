package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.in.PaymentCallbackRequestDto;

import java.util.List;

public interface PaymentService {

    void processPaymentCallback(PaymentCallbackRequestDto paymentCallbackRequestDto);

    void test(String memberUuid, List<String> productUuid);
}
