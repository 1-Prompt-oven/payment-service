package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.in.PaymentCookieRequestDto;
import com.promptoven.paymentservice.member.payment.dto.in.PaymentProductRequestDto;
import com.promptoven.paymentservice.member.payment.dto.out.PaymentListResponseDto;

import java.util.List;

public interface PaymentService {

    void paymentProduct(PaymentProductRequestDto paymentProductRequestDto);

    void paymentCookie(PaymentCookieRequestDto paymentCookieRequestDto);

    List<PaymentListResponseDto> getPaymentHistoryList(String memberUuid);

    PaymentListResponseDto getPaymentHistory(Long paymentId);

    //    void processPaymentCallback(PaymentCallbackRequestDto paymentCallbackRequestDto);
    //
    //    void processCookiePaymentCallback(PaymentCookieRequestDto requestDto);
    //
    //    void test(String memberUuid, List<String> productUuid);
    //
    //    void testCookiePayment(String memberUuid, Integer cookieAmount);
}
