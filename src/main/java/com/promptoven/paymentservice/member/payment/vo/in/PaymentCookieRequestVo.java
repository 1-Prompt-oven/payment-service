package com.promptoven.paymentservice.member.payment.vo.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentCookieRequestVo {

    private final String paymentKey;
    private final String orderId;
    private final Integer amount;
    private final Integer cookieAmount;
    private final String memberUuid;
}
