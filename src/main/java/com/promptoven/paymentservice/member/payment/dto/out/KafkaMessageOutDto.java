package com.promptoven.paymentservice.member.payment.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
public class KafkaMessageOutDto {

    private Long paymentId;

    private String memberUuid;

    private List<String> productUuids;

    private Map<String, String> productSellerMap;

    public static KafkaMessageOutDto toDto(Long paymentId, String memberUuid, List<String> productUuids, Map<String, String> productSellerMap) {
        return KafkaMessageOutDto.builder()
                .paymentId(paymentId)
                .memberUuid(memberUuid)
                .productUuids(productUuids)
                .productSellerMap(productSellerMap)
                .build();
    }
}
