package com.promptoven.paymentservice.member.payment.dto.out;

import com.promptoven.paymentservice.common.domain.Payment;
import com.promptoven.paymentservice.member.payment.vo.out.PaymentListResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PaymentListResponseDto {

    private Long paymentId;
    private String memberUuid;
    private Integer totalAmount;
    private String message;
    private String orderId;
    private String orderName;
    private String paymentMethod;
    private String paymentWay;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;

    public static PaymentListResponseVo toVo(PaymentListResponseDto dto) {
        return PaymentListResponseVo.builder()
                .paymentId(dto.getPaymentId())
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

    public static PaymentListResponseDto fromEntity(Payment payment) {
        return PaymentListResponseDto.builder()
                .paymentId(payment.getPaymentId())
                .memberUuid(payment.getMemberUuid())
                .totalAmount(payment.getTotalAmount())
                .message(payment.getMessage())
                .orderId(payment.getOrderId())
                .orderName(payment.getOrderName())
                .paymentMethod(payment.getPaymentMethod())
                .paymentWay(payment.getPaymentWay())
                .requestedAt(payment.getRequestedAt())
                .approvedAt(payment.getApprovedAt())
                .build();
    }
}
