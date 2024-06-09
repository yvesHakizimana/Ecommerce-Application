package com.rca.ecommerce.kafka;

import com.rca.ecommerce.email.EmailService;
import com.rca.ecommerce.kafka.order.OrderConfirmation;
import com.rca.ecommerce.kafka.payment.PaymentConfirmation;
import com.rca.ecommerce.notification.Notification;
import com.rca.ecommerce.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.rca.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.rca.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation)  throws MessagingException {
        log.info("Consuming the payment confirmation from payment-topic:: {}", paymentConfirmation);
        var notification = Notification.builder()
                .type(PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();
        notificationRepository.save(notification);
        var customerName = getFullNameOfCustomer(paymentConfirmation.customerFirstName(), paymentConfirmation.customerLastName());
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException{
        log.info("Consuming the order confirmation order-topic:: {}", orderConfirmation);
        var notification = Notification.builder()
                .type(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();
        notificationRepository.save(notification);
        var customerName = getFullNameOfCustomer(orderConfirmation.customer().firstName(), orderConfirmation.customer().lastName());
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.productsPurchased()
        );
    }

    public String getFullNameOfCustomer(String firstName, String lastName) {
        return  firstName + " " + lastName;
    }
}
