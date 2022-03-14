package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.models.InventoryModel;
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

    public long traitorsPercentage(){
        long totalPeople = rebelRepository.count();

        long totalTraitors = rebelRepository.countByTraitor(true);

        long traitorsPercentage = (totalTraitors/totalPeople)*100;

        return traitorsPercentage;
    }

    public long rebelsPercentage(){
        return 100-traitorsPercentage();
    }

    public double[] rebelsResourcesMeans(){
        List<InventoryModel> rebelsInventoryList = rebelRepository.findAllByTraitor(false).stream().map(rebel -> rebel.getInventory()).collect(Collectors.toList());

        long totalRebels = rebelRepository.countByTraitor(false);

        int rebelsTotalWeapons = rebelsInventoryList.stream()
                .map(inv -> inv.getWeapon())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int rebelsTotalAmmo = rebelsInventoryList.stream()
                .map(inv -> inv.getAmmo())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int rebelsTotalWater = rebelsInventoryList.stream()
                .map(inv -> inv.getWater())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int rebelsTotalFood = rebelsInventoryList.stream()
                .map(inv -> inv.getFood())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        //Requisito 3
        double[] rebelsInventoryMeans = {rebelsTotalWeapons/totalRebels , rebelsTotalAmmo/totalRebels , rebelsTotalFood/totalRebels , rebelsTotalWater/totalRebels};

        return rebelsInventoryMeans;
    }

    public int traitorsTotalPoints(){
        List<InventoryModel> traitorsInventoryList = rebelRepository.findAllByTraitor(true).stream().map(rebel -> rebel.getInventory()).collect(Collectors.toList());

        int traitorsTotalWeapons = traitorsInventoryList.stream()
                .map(inv -> inv.getWeapon())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int traitorsTotalAmmo = traitorsInventoryList.stream()
                .map(inv -> inv.getAmmo())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int traitorsTotalWater = traitorsInventoryList.stream()
                .map(inv -> inv.getWater())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int traitorsTotalFood = traitorsInventoryList.stream()
                .map(inv -> inv.getFood())
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        //Requisito 4
        int traitorsPointsSum = 4*traitorsTotalWeapons + 3*traitorsTotalAmmo + 2*traitorsTotalWater + traitorsTotalFood;

        return traitorsPointsSum;
    }

}
