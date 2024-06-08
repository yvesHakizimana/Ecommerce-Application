package com.rca.ecommerce.customer;

public record CustomerResponse(
        String customerId,
        String firstName,
        String lastName,
        String email
) {
}
