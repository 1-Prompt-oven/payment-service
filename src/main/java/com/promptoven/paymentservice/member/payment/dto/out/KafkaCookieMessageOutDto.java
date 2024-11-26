package com.promptoven.paymentservice.member.payment.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class KafkaCookieMessageOutDto {

    private Long paymentId;

    private String memberUuid;

    private Integer cookieAmount;

    private LocalDateTime approvedAt;

    public static KafkaCookieMessageOutDto toDto(Long paymentId, String memberUuid, Integer cookieAmount, LocalDateTime approvedAt) {
        return KafkaCookieMessageOutDto.builder()
                .paymentId(paymentId)
                .memberUuid(memberUuid)
                .cookieAmount(cookieAmount)
                .approvedAt(approvedAt)
                .build();
    }
}
