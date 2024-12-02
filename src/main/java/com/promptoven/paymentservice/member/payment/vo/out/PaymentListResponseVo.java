package com.promptoven.paymentservice.member.payment.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PaymentListResponseVo {

    private final Long paymentId;
    private final String memberUuid;
    private final Integer totalAmount;
    private final String message;
    private final String orderId;
    private final String orderName;
    private final String paymentMethod;
    private final String paymentWay;
    private final LocalDateTime requestedAt;
    private final LocalDateTime approvedAt;
}
