package com.rca.ecommerce.exception;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class BusinessException  extends RuntimeException{
    private final String message;
}
