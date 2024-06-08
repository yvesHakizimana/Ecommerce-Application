package com.rca.ecommerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;


    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest  orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

}
