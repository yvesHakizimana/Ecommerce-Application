package com.rca.ecommerce.kafka;

import com.rca.ecommerce.customer.CustomerResponse;
import com.rca.ecommerce.order.PaymentMethod;
import com.rca.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> productsPurchased
) {
}
