package com.promptoven.paymentservice.member.payment.dto.out;

import com.promptoven.paymentservice.common.domain.PaymentWay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PaymentDetailResponseDto {
    private String paymentKey; // 결제 고유 키
    private String orderId; // 주문 ID
    private Integer amount; // 결제 금액
    private String paymentWay; // 결제 수단 (카드, 계좌이체 등)

    // 결제 상태
    private String status; // 결제 상태 (e.g., READY, IN_PROGRESS, DONE, CANCELED)
    private String transactionKey; // 거래 고유 키

    // 결제 수단에 따른 추가 정보 (예: 카드 결제일 경우 카드 정보)
    private String cardCompany; // 카드사 이름
    private String cardNumber; // 카드 번호 (마스킹 처리)
    private String installmentPlanMonths; // 할부 개월 수 (0일 경우 일시불)
    private String cardType; // 카드 타입 (e.g., CREDIT)
    private String ownerType; // 카드 소유자 타입 (e.g., 개인, 법인)

    // 현금 영수증 정보
    private String cashReceiptNumber; // 현금 영수증 번호

    // 계좌이체 정보
    private String bank; // 은행 이름
    private String accountNumber; // 계좌 번호 (마스킹 처리)

    // 결제 승인/거절 정보
    private LocalDateTime approvedAt; // 결제 승인 시각
    private LocalDateTime canceledAt; // 결제 취소 시각 (취소된 경우)
    private String cancelReason; // 취소 사유 (있을 경우)

    // 추가 정보
    private String receiptUrl; // 영수증 URL
    private String requestedAt; // 결제 요청 시각
    private String currency; // 결제 화폐 종류 (e.g., KRW)

    // 가상 계좌 정보 (가상 계좌로 결제 시)
    private String virtualAccountNumber; // 가상 계좌 번호
    private String dueDate; // 입금 만료 기한

    // 기타 상세 정보
    private String approveNumber; // 승인 번호
    private String failReason; // 결제 실패 사유 (결제가 실패한 경우)
}
