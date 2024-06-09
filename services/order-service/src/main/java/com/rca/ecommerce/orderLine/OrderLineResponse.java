package com.rca.ecommerce.orderLine;

public record OrderLineResponse(
        Integer orderLineId,
        double quantity
) {
}
