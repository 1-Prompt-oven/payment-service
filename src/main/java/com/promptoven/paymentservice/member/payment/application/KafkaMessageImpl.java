package com.promptoven.paymentservice.member.payment.application;

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

    @Override
    public void createPaymentMessage(KafkaMessageOutDto messageOutDto) {
        messageTemplate(CREATE_TOPIC, messageOutDto);
    }

    private void messageTemplate(String topic, KafkaMessageOutDto messageOutDto) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, messageOutDto);
        kafkaTemplate.send(record);
    }
}
