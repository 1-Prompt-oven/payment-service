package com.promptoven.paymentservice.common.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 50)
    private String memberUuid;

    @Comment("결제금액")
    @Column(nullable = false)
    private Integer totalAmount;

    @Comment("결제 메시지")
    @Column(nullable = false, length = 100)
    private String message;

    @Comment("주문 ID")
    @Column(nullable = false, length = 50)
    private String orderId;

    @Comment("주문명")
    @Column(nullable = false, length = 100)
    private String orderName;

    @Comment("결제 수단")
    @Column(nullable = false)
    private String paymentMethod;

    @Comment("결제 유형")
    @Column(nullable = false, length = 50)
    private String paymentWay;

    @Comment("요청시간")
    @Column(nullable = false, length = 50)
    private LocalDateTime requestedAt;

    @Comment("결제시간")
    @Column(nullable = false)
    private LocalDateTime approvedAt;

    @Builder
    public Payment(Long paymentId, String memberUuid, Integer totalAmount, String message, String orderId, String orderName, String paymentMethod, String paymentWay, LocalDateTime requestedAt, LocalDateTime approvedAt) {
        this.paymentId = paymentId;
        this.memberUuid = memberUuid;
        this.totalAmount = totalAmount;
        this.message = message;
        this.orderId = orderId;
        this.orderName = orderName;
        this.paymentMethod = paymentMethod;
        this.paymentWay = paymentWay;
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
    }
}
