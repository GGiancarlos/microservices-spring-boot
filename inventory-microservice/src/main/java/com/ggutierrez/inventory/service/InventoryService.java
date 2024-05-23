package com.ggutierrez.inventory.service;

import com.ggutierrez.inventory.model.dto.BaseResponse;
import com.ggutierrez.inventory.model.dto.OrderItemRequest;
import com.ggutierrez.inventory.model.entity.Inventory;
import com.ggutierrez.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku) {
        Optional<Inventory> item = inventoryRepository.findBySku(sku);
        return item.isPresent();
    }

    public BaseResponse isPresent(List<OrderItemRequest> orderItemRequest) {

        String[] errors = {};
        List<String> errorMessages = new ArrayList<>();


        for (OrderItemRequest orderItemRequestItem : orderItemRequest) {
            Optional<Inventory> item = inventoryRepository.findBySku(orderItemRequestItem.getSku());
            if (item.isEmpty()) {
                errorMessages.add("Order item not found");
            } else if (item.get().getQuantity() < orderItemRequestItem.getQuantity()) {
                errorMessages.add("Order items insufficient for quantity");
            }
        }
        return new BaseResponse(errorMessages.toArray(errors));
    }

}
