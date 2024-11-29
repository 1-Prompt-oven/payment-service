package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.out.KafkaCookieMessageOutDto;
import com.promptoven.paymentservice.member.payment.dto.out.KafkaMessageOutDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageImpl implements Message {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${payment-create-event}")
    private String CREATE_TOPIC;

    @Value("${payment-cookie-create-event}")
    private String CREATE_COOKIE_TOPIC;

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