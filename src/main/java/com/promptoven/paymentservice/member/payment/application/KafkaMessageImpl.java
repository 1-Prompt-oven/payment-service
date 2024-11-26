package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.out.KafkaCookieMessageOutDto;
import com.promptoven.paymentservice.member.payment.dto.out.KafkaMessageOutDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageImpl implements Message {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String CREATE_TOPIC = "payment-create-event";
    private static final String CREATE_COOKIE_TOPIC = "payment-cookie-create-event";

    @Override
    public void createPaymentMessage(KafkaMessageOutDto messageOutDto) {
        messageTemplate(CREATE_TOPIC, messageOutDto);
    }

    @Override
    public void createCookiePaymentMessage(KafkaCookieMessageOutDto messageOutDto) {
        messageTemplate(CREATE_COOKIE_TOPIC, messageOutDto);
    }

    private void messageTemplate(String topic, Object messageOutDto) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, messageOutDto);
        kafkaTemplate.send(record);
    }
}