package com.promptoven.paymentservice.common.domain;

public enum PaymentWay {
    CARD("card"),            // 카드 결제
    CASH("cash"),            // 현금 결제
    TRANSFER("transfer"),    // 계좌이체
    VIRTUAL_ACCOUNT("virtualAccount"), // 가상 계좌
    MOBILE("mobile"),        // 휴대폰 결제
    POINT("point"),          // 포인트 결제
    UNKNOWN("unknown");      // 기타 결제 수단

    private final String tossValue;

    PaymentWay(String tossValue) {
        this.tossValue = tossValue;
    }

    public static PaymentWay from(String tossValue) {
        for (PaymentWay way : values()) {
            if (way.tossValue.equalsIgnoreCase(tossValue)) {
                return way;
            }
        }
        return UNKNOWN; // 알 수 없는 결제 수단일 경우
    }
}
