package com.rca.ecommerce.payment;

import com.rca.ecommerce.customer.CustomerResponse;
import com.rca.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
