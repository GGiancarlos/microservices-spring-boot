package com.ggutierrez.orders.controller;

import com.ggutierrez.orders.model.dto.OrderRequest;
import com.ggutierrez.orders.model.dto.OrderResponse;
import com.ggutierrez.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return "Order created";
    }

    @GetMapping
    public List<OrderResponse> getOrders() {
        return orderService.findAll();
    }
}
