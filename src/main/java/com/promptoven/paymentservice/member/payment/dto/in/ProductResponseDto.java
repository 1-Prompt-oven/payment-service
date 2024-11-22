package com.promptoven.paymentservice.member.payment.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseDto {
    private String productUuid;
    private String sellerUuid;
}
