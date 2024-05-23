package com.ggutierrez.inventory.controller;

import com.ggutierrez.inventory.model.dto.BaseResponse;
import com.ggutierrez.inventory.model.dto.OrderItemRequest;
import com.ggutierrez.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku}")
    public boolean isInStock(@PathVariable String sku) {
        return inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    public BaseResponse inStock(@RequestBody List<OrderItemRequest> orderItemRequest) {
        return inventoryService.isPresent(orderItemRequest);
    }

}
