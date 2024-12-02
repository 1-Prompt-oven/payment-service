package com.promptoven.paymentservice.member.payment.dto.in;

import com.promptoven.paymentservice.common.domain.Payment;
import com.promptoven.paymentservice.member.payment.vo.in.PaymentProductRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class PaymentProductRequestDto {

    private String memberUuid;
    private List<String> purchaseList;
    private Integer totalAmount;
    private String message;
    private String orderId;
    private String orderName;
    private String paymentMethod;
    private String paymentWay;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;

    public static PaymentProductRequestDto toDto(PaymentProductRequestVo vo) {
        return PaymentProductRequestDto.builder()
                .memberUuid(vo.getMemberUuid())
                .purchaseList(vo.getPurchaseList())
                .totalAmount(vo.getTotalAmount())
                .message(vo.getMessage())
                .orderId(vo.getOrderId())
                .orderName(vo.getOrderName())
                .paymentMethod(vo.getPaymentMethod())
                .paymentWay(vo.getPaymentWay())
                .requestedAt(vo.getRequestedAt())
                .approvedAt(vo.getApprovedAt())
                .build();
    }

    public static Payment toEntity(PaymentProductRequestDto dto) {
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
