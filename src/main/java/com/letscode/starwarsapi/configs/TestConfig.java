package com.letscode.starwarsapi.configs;

import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import com.letscode.starwarsapi.repositories.InventoryRepository;
import com.letscode.starwarsapi.repositories.LocalizationRepository;
import com.letscode.starwarsapi.repositories.RebelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private RebelRepository rebelRepository;

    public TestConfig(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LocalizationModel firstLocalization = new LocalizationModel(1L, 1L, "firstBase");
        LocalizationModel secondLocalization = new LocalizationModel(1L, 1L, "secondBase");
        LocalizationModel thirdLocalization = new LocalizationModel(1L, 1L, "thirdBase");
        LocalizationModel fourthLocalization = new LocalizationModel(1L, 1L, "fourthBase");

        InventoryModel firstInventory = new InventoryModel(1, 1, 1, 1);
        InventoryModel secondInventory = new InventoryModel(2, 2, 2, 2);
        InventoryModel thirdInventory = new InventoryModel(3, 3, 3, 3);
        InventoryModel fourthInventory = new InventoryModel(4, 4, 4, 4);

        RebelModel firstRebel = new RebelModel("Gustavo", 25, GenderEnum.MALE, firstLocalization, firstInventory);
        RebelModel secondRebel = new RebelModel("Mayara", 25, GenderEnum.FEMALE, secondLocalization, secondInventory);
        RebelModel thirdRebel = new RebelModel("Guilherme", 25, GenderEnum.MALE, thirdLocalization, thirdInventory);
        RebelModel fourthRebel = new RebelModel("Geovanna", 25, GenderEnum.FEMALE, fourthLocalization, fourthInventory);

        rebelRepository.saveAll(Arrays.asList(firstRebel, secondRebel, thirdRebel, fourthRebel));
    }
}
