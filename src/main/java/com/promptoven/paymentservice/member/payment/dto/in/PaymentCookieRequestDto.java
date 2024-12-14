package com.promptoven.paymentservice.member.payment.dto.in;

import com.promptoven.paymentservice.common.domain.Payment;
import com.promptoven.paymentservice.member.payment.vo.in.PaymentCookieRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PaymentCookieRequestDto {

    private String memberUuid;
    private Integer cookieAmount;
    private Integer totalAmount;
    private String message;
    private String orderId;
    private String orderName;
    private String paymentMethod;
    private String paymentWay;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;

    public static PaymentCookieRequestDto toDto(PaymentCookieRequestVo vo) {
        return PaymentCookieRequestDto.builder()
                .memberUuid(vo.getMemberUuid())
                .cookieAmount(vo.getCookieAmount())
                .totalAmount(vo.getTotalAmount())
                .message(vo.getMessage())
                .orderId(vo.getOrderId())
                .orderName(vo.getOrderName())
                .paymentMethod(vo.getPaymentMethod())
                .paymentWay(vo.getPaymentWay())
                .requestedAt(LocalDateTime.now())
                .approvedAt(LocalDateTime.now())
                .build();
    }

    public static Payment toEntity(PaymentCookieRequestDto dto) {
        return Payment.builder()
                .memberUuid(dto.getMemberUuid())
                .totalAmount(dto.getTotalAmount())
                .message(dto.getMessage())
                .orderId(dto.getOrderId())
                .orderName(dto.getOrderName())
                .paymentMethod(dto.getPaymentMethod())
                .paymentWay(dto.getPaymentWay())
                .requestedAt(dto.getRequestedAt())
                .approvedAt(dto.getApprovedAt())
                .build();
    }
}
