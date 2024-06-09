package com.rca.ecommerce.orderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest){
            var order = orderLineMapper.toOrderLine(orderLineRequest);
            return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findByOrderId(orderId).stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
