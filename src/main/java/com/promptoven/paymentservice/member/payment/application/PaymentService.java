package com.promptoven.paymentservice.member.payment.application;

public interface PaymentService {

    void processPaymentCallback(String paymentKey, String orderId, Integer amount, String productUuid);

    void test(String productUuid);
}
