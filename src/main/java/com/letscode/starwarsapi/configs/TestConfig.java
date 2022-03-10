package com.letscode.starwarsapi.configs;

import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.repositories.InventoryRepository;
import com.letscode.starwarsapi.repositories.LocalizationRepository;
import com.letscode.starwarsapi.repositories.RebelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.UUID;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private RebelRepository rebelRepository;
    private LocalizationRepository localizationRepository;
    private InventoryRepository inventoryRepository;

    public TestConfig(RebelRepository rebelRepository, LocalizationRepository localizationRepository, InventoryRepository inventoryRepository) {
        this.rebelRepository = rebelRepository;
        this.localizationRepository = localizationRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LocalizationModel firstLocalization = new LocalizationModel(UUID.randomUUID(), 1L, 1L, "firstBase");
        LocalizationModel secondLocalization = new LocalizationModel(UUID.randomUUID(), 1L, 1L, "secondBase");
        LocalizationModel thirdLocalization = new LocalizationModel(UUID.randomUUID(), 1L, 1L, "thirdBase");
        LocalizationModel fourthLocalization = new LocalizationModel(UUID.randomUUID(), 1L, 1L, "fourthBase");

        InventoryModel firstInventory = new InventoryModel(UUID.randomUUID(), 1, 1, 1, 1);
        InventoryModel secondInventory = new InventoryModel(UUID.randomUUID(), 2, 2, 2, 2);
        InventoryModel thirdInventory = new InventoryModel(UUID.randomUUID(), 3, 3, 3, 3);
        InventoryModel fourthInventory = new InventoryModel(UUID.randomUUID(), 4, 4, 4, 4);

        RebelModel firstRebel = new RebelModel(UUID.randomUUID(), "Gustavo", 25, "male", firstLocalization, firstInventory);
        RebelModel secondRebel = new RebelModel(UUID.randomUUID(), "Mayara", 25, "female", secondLocalization, secondInventory);
        RebelModel thirdRebel = new RebelModel(UUID.randomUUID(), "Guilherme", 25, "male", thirdLocalization, thirdInventory);
        RebelModel fourthRebel = new RebelModel(UUID.randomUUID(), "Geovanna", 25, "female", fourthLocalization, fourthInventory);

        localizationRepository.saveAll(Arrays.asList(firstLocalization, secondLocalization, thirdLocalization, fourthLocalization));
        inventoryRepository.saveAll(Arrays.asList(firstInventory, secondInventory, thirdInventory, fourthInventory));
        rebelRepository.saveAll(Arrays.asList(firstRebel, secondRebel, thirdRebel, fourthRebel));
    }
}
