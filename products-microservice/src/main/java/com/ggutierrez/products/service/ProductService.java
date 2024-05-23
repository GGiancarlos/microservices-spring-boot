package com.ggutierrez.products.service;

import com.ggutierrez.products.model.dto.ProductRequest;
import com.ggutierrez.products.model.dto.ProductResponse;
import com.ggutierrez.products.model.entity.Product;
import com.ggutierrez.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
            .stream()
            .map(ProductResponse::toProductResponse)
            .toList();
    }

    public void addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
            .sku(productRequest.getSku())
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
            .status(productRequest.getStatus())
            .build();

        productRepository.save(product);

        log.info("Added product: {}", product);
    }
}
