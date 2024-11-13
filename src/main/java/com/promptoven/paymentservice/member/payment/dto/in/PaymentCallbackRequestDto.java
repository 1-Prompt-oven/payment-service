package com.promptoven.paymentservice.member.payment.dto.in;

import com.promptoven.paymentservice.member.payment.vo.in.PaymentCallbackRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaymentCallbackRequestDto {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private List<String> productUuid;
    private String memberUuid;

    public static PaymentCallbackRequestDto toDto(PaymentCallbackRequestVo vo) {
        return PaymentCallbackRequestDto.builder()
                .paymentKey(vo.getPaymentKey())
                .orderId(vo.getOrderId())
                .amount(vo.getAmount())
                .productUuid(vo.getProductUuid())
                .memberUuid(vo.getMemberUuid())
                .build();
    }
}