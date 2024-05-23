package com.ggutierrez.orders.service;

import com.ggutierrez.orders.model.dto.*;
import com.ggutierrez.orders.model.entity.Order;
import com.ggutierrez.orders.model.entity.OrderItem;
import com.ggutierrez.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
            .map(this::toOrderResponse)
            .toList();
    }

    public void createOrder(OrderRequest orderRequest) {

        // verify stock ms_inventory calling to other ms
        BaseResponse result = webClientBuilder.build()
            .post()
            .uri("lb://microservice-inventory/api/inventory/in-stock")
            .bodyValue(orderRequest.getOrderItems())
            .retrieve()
            .bodyToMono(BaseResponse.class)
            .block();

        if(result != null && !result.hasErrors()) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                .map(orderItem -> mapOrderItemRequestToOrderItem(orderItem, order))
                .toList());

            orderRepository.save(order);

            log.info("Order created: {}", order);
        } else {
            log.error("Order could not be created: {}", result);
            throw new IllegalArgumentException("Some of the products are not in stock");
        }

    }

    private OrderItem mapOrderItemRequestToOrderItem(OrderItemRequest orderItem, Order order) {
        return OrderItem.builder()
            .id(orderItem.getId())
            .sku(orderItem.getSku())
            .price(orderItem.getPrice())
            .quantity(orderItem.getQuantity())
            .order(order)
            .build();
    }

    private OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
            .id(order.getId())
            .orderNumber(order.getOrderNumber())
            .orderItems(order.getOrderItems().stream()
                .map(this::toOrderItemResponse).toList())
            .build();
    }

    private OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
            .id(orderItem.getId())
            .sku(orderItem.getSku())
            .price(orderItem.getPrice())
            .quantity(orderItem.getQuantity())
            .build();
    }


}
