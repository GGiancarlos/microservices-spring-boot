package com.ggutierrez.products.model.dto;

import com.ggutierrez.products.model.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .sku(product.getSku())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .status(product.getStatus())
            .build();
    }
}
