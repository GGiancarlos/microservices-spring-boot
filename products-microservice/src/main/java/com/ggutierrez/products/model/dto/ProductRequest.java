package com.ggutierrez.products.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;
}
