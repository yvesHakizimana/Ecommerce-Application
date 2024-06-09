package com.rca.ecommerce.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        @Positive(message = "The amount must be a positive value")
        BigDecimal amount,
        PaymentMethod paymentMethod,
        @NotNull(message = "The Order must be specified")
        Integer orderId,
        @NotNull(message = "The Order Reference is required")
        String orderReference,
        Customer customer
) {
}
