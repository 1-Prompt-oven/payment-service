package com.promptoven.paymentservice.member.payment.vo.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentCookieRequestVo {

    private String paymentKey;
    private String orderId;
    private Integer amount;
    private Integer cookieAmount;
    private String memberUuid;
}
