package com.promptoven.paymentservice.member.payment.vo.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class PaymentProductRequestVo {

    private final String memberUuid;
    private final List<String> purchaseList;
    private final Integer totalAmount;
    private final String message;
    private final String orderId;
    private final String orderName;
    private final String paymentMethod;
    private final String paymentWay;
    private final LocalDateTime requestedAt;
    private final LocalDateTime approvedAt;
}
