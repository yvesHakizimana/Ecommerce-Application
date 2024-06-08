package com.rca.ecommerce.exceptions;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ProductPurchaseException  extends  RuntimeException{
    private final String message;
}
