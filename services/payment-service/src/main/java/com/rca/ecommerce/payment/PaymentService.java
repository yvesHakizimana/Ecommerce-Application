package com.rca.ecommerce.payment;

import com.rca.ecommerce.notification.NotificationProducer;
import com.rca.ecommerce.notification.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;


    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));

        //Sending the Payment Notification Information to Notification Microservice.
        notificationProducer.sendNotification(new PaymentConfirmation(
                paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstName(),
                paymentRequest.customer().lastName(),
                paymentRequest.customer().email()));

        return payment.getId();
    }
}
