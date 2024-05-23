package com.ggutierrez.orders.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private List<OrderItemResponse> orderItems;
}
