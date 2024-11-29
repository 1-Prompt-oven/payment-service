package com.promptoven.paymentservice.member.payment.vo.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PaymentCallbackRequestVo {

    private final String paymentKey;
    private final String orderId;
    private final Integer amount;
    private final List<String> productUuid;
    private final String memberUuid;
}