package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.out.KafkaCookieMessageOutDto;
import com.promptoven.paymentservice.member.payment.dto.out.KafkaMessageOutDto;

public interface Message {

    void createPaymentMessage(KafkaMessageOutDto messageOutDto);

    void createCookiePaymentMessage(KafkaCookieMessageOutDto messageOutDto);
}
