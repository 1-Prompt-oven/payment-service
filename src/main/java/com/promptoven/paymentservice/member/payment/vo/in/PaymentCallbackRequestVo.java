package com.promptoven.paymentservice.member.payment.vo.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaymentCallbackRequestVo {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private List<String> productUuid;
    private String memberUuid;
}