package com.ggutierrez.products.controller;


import com.ggutierrez.products.model.dto.ProductRequest;
import com.ggutierrez.products.model.dto.ProductResponse;
import com.ggutierrez.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductResponse> getAll() {
        return productService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }
}
