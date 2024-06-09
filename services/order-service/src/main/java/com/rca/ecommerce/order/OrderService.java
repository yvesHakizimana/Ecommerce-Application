package com.rca.ecommerce.order;

import com.rca.ecommerce.customer.CustomerClient;
import com.rca.ecommerce.exception.BusinessException;
import com.rca.ecommerce.kafka.OrderConfirmation;
import com.rca.ecommerce.kafka.OrderProducer;
import com.rca.ecommerce.orderLine.OrderLineRequest;
import com.rca.ecommerce.orderLine.OrderLineService;
import com.rca.ecommerce.payment.PaymentClient;
import com.rca.ecommerce.payment.PaymentRequest;
import com.rca.ecommerce.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        //Check if we have the customer --> Communicated  Customer MicroService using OpenFeign
        var customer = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order:: No Customer Exists with the provided ID::" + orderRequest.customerId()));

        //Purchasing the products within The order--> Product Microservice communicated using ---(Rest Template)
        var productsPurchased = productClient.purchaseProducts(orderRequest.products());

        //Persist/Saving the order/ Order Lines
        var order = orderRepository.save(orderMapper.toOrder(orderRequest));
        for(var purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }
        // Starting the payment Process
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
                );
        //Sending the order details to Payment MicroService.
        paymentClient.requestOrderPayment(paymentRequest);

        //Send the order confirmation --> to the notification Microservice.
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer, productsPurchased));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::fromOrder).toList();
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Can not find order by ID::" + orderId));
    }


}
