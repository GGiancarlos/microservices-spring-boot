package com.ggutierrez.inventory.util;

import com.ggutierrez.inventory.model.entity.Inventory;
import com.ggutierrez.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data loading...");
        if (inventoryRepository.findAll().isEmpty()) {
            inventoryRepository.saveAll(
                List.of(
                    Inventory.builder().sku("000001").quantity(100L).build(),
                    Inventory.builder().sku("000002").quantity(20L).build(),
                    Inventory.builder().sku("000003").quantity(30L).build(),
                    Inventory.builder().sku("000004").quantity(0L).build()
                )
            );
        }
        log.info("Finished data loading.");
    }
}
