package com.ggutierrez.orders.model.dto;

import lombok.Data;


@Data
public class OrderItemRequest {
    private Long id;
    private String sku;
    private Double price;
    private Long quantity;
}
