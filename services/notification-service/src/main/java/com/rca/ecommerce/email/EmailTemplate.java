package com.rca.ecommerce.email;

import lombok.Getter;

@Getter
public enum EmailTemplate {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation");

    private final String template;
    private final String subject;

    EmailTemplate(String templateName, String subject) {
        this.template = templateName;
        this.subject = subject;
    }
}
