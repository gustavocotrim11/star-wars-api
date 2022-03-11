package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.repositories.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    public final RebelRepository rebelRepository;

    public ReportService(RebelRepository rebelRepository){
        this.rebelRepository = rebelRepository;
    }

    public void generateReport(){
        List<InventoryModel> rebelsInventoryList = rebelRepository.findAllByTraitor(false).stream().map(rebel -> rebel.getInventory()).collect(Collectors.toList());

        List<InventoryModel> traitorsInventoryList = rebelRepository.findAllByTraitor(true).stream().map(rebel -> rebel.getInventory()).collect(Collectors.toList());

        long totalPeople = rebelRepository.count();

        long totalTraitors = rebelRepository.countByTraitor(true);

        long totalRebels = totalPeople-totalTraitors;

        long traitorsPercentage = (totalTraitors/totalPeople)*100;

        long rebelsPercentage = 100-traitorsPercentage;

        int totalWeapom = rebelsInventoryList.stream()
                .map(inv -> inv.getWeapon())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int totalAmmo = rebelsInventoryList.stream()
                .map(inv -> inv.getAmmo())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int totalWater = rebelsInventoryList.stream()
                .map(inv -> inv.getWater())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int totalFood = rebelsInventoryList.stream()
                .map(inv -> inv.getFood())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        double[] rebelsInventoryMeans = {totalWeapom/totalRebels , totalAmmo/totalRebels , totalFood/totalRebels , totalWater/totalRebels};




    }

}
