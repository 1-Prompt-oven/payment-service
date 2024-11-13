package com.promptoven.paymentservice.member.payment.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class KafkaMessageOutDto {

    private Long paymentId;

    private String memberUuid;

    private List<String> productUuid;

    public static KafkaMessageOutDto toDto(Long paymentId, String memberUuid, List<String> productUuid) {
        return KafkaMessageOutDto.builder()
                .paymentId(paymentId)
                .memberUuid(memberUuid)
                .productUuid(productUuid)
                .build();
    }
}
