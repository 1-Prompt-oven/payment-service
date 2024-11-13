package com.promptoven.paymentservice.member.payment.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KafkaMessageOutDto {

    private String productUuid;

    public static KafkaMessageOutDto toDto(String productUuid) {
        return KafkaMessageOutDto.builder()
                .productUuid(productUuid)
                .build();
    }
}
