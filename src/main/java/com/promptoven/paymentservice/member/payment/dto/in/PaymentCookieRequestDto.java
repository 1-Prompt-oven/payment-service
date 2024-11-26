package com.promptoven.paymentservice.member.payment.dto.in;

import com.promptoven.paymentservice.member.payment.vo.in.PaymentCookieRequestVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentCookieRequestDto {

    private String paymentKey;
    private String orderId;
    private Integer amount;
    private Integer cookieAmount;
    private String memberUuid;

    public static PaymentCookieRequestDto toDto(PaymentCookieRequestVo vo) {
        return PaymentCookieRequestDto.builder()
                .paymentKey(vo.getPaymentKey())
                .orderId(vo.getOrderId())
                .amount(vo.getAmount())
                .cookieAmount(vo.getCookieAmount())
                .memberUuid(vo.getMemberUuid())
                .build();
    }
}
