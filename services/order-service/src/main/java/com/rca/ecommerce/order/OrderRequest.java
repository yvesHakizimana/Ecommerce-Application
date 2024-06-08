package com.rca.ecommerce.order;

import com.rca.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should not be null")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotBlank(message = "Customer should not be blank")
        @NotEmpty(message = "Customer should not be empty")
        String customerId,
        @NotEmpty(message = "You should at least  purchase one product")
        List<PurchaseRequest> products
        ) {
}
