package com.ggutierrez.orders.model.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderItemResponse {
    private Long id;
    private String sku;
    private Double price;
    private Long quantity;
}
