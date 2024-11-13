package com.promptoven.paymentservice.member.payment.vo.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PaymentCallbackRequestVo {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private String productUuid;
    private String memberUuid;
}