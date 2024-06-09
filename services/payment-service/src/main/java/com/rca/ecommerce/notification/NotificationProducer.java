package com.rca.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private  final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public void sendNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Sending notification with body {}", paymentConfirmation);
        Message<PaymentConfirmation> paymentNotification = MessageBuilder
                .withPayload(paymentConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(paymentNotification);
    }
}
