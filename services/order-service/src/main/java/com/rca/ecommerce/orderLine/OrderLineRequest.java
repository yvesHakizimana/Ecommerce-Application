package com.rca.ecommerce.orderLine;

public record OrderLineRequest(
        Integer orderLineId,
        Integer orderId,
        Integer productId,
        double productQuantity
) {
}
