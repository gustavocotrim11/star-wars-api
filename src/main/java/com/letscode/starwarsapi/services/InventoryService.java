package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.dtos.InventoryDto;
import com.letscode.starwarsapi.dtos.NegotiateDTO;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.ItemPointEnum;
import com.letscode.starwarsapi.repositories.InventoryRepository;
import com.letscode.starwarsapi.repositories.RebelRepository;
import com.letscode.starwarsapi.services.exceptions.RebelNotFoundException;
import com.letscode.starwarsapi.services.exceptions.TraitorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final RebelService rebelService;

    public InventoryService(InventoryRepository repository, RebelService rebelService) {
        this.repository = repository;
        this.rebelService = rebelService;
    }

    public void trade(UUID donorId, UUID receiverId, NegotiateDTO negotiateDTO) {

        RebelModel rebel1 = rebelService.findById(donorId);
        RebelModel rebel2 = rebelService.findById(receiverId);

        //verificar se os IDs existem

        if (rebel1.getTraitor() || rebel2.getTraitor()) {
            throw new TraitorException();
        }

        if (rebel1.getInventory() != null && negotiateDTO.getItemsToNegotiate1() != null) {
            var validateQuantity1 = validateQuantityOfItems(negotiateDTO.getItemsToNegotiate1(), rebel1);
            var validateQuantity2 = validateQuantityOfItems(negotiateDTO.getItemsToNegotiate2(), rebel2);

            var sumOfItemsToBeNegotiated1 = sumOfItemsToBeNegotiated(negotiateDTO.getItemsToNegotiate1());
            var sumOfItemsToBeNegotiated2 = sumOfItemsToBeNegotiated(negotiateDTO.getItemsToNegotiate2());

            if (sumOfItemsToBeNegotiated1 == sumOfItemsToBeNegotiated2) {

                changeItems(rebel1, rebel2, negotiateDTO.getItemsToNegotiate1());
                changeItems(rebel2, rebel1, negotiateDTO.getItemsToNegotiate2());

                rebelService.save(rebel1);
                rebelService.save(rebel2);
            }
        }
    }

    private boolean validateQuantityOfItems(InventoryDto inventoryDto, RebelModel rebel) {
        return (rebel.getInventory().getWeapon() >= inventoryDto.getWeapon()) &&
                (rebel.getInventory().getAmmo() >= inventoryDto.getAmmo()) &&
                (rebel.getInventory().getFood() >= inventoryDto.getFood()) &&
                (rebel.getInventory().getWater() >= inventoryDto.getWater());
    }

    private int sumOfItemsToBeNegotiated(InventoryDto inventoryDto) {
        var sumOfItems =
                (inventoryDto.getWeapon() * ItemPointEnum.WEAPON.getPoint() + inventoryDto.getAmmo() * ItemPointEnum.AMMO.getPoint() + inventoryDto.getFood() * ItemPointEnum.FOOD.getPoint() + inventoryDto.getWater() * ItemPointEnum.WATER.getPoint());
        return sumOfItems;
    }

    private void changeItems(RebelModel rebelOrigem, RebelModel reberDestino,
                             InventoryDto inventarioOrigem) {


        if (rebelOrigem.getInventory().getWeapon() > 0) {

            rebelOrigem.getInventory().setWeapon(rebelOrigem.getInventory().getWeapon() - inventarioOrigem.getWeapon());
            reberDestino.getInventory().setWeapon(reberDestino.getInventory().getWeapon() + inventarioOrigem.getWeapon());

        }
        if (rebelOrigem.getInventory().getAmmo() > 0) {

            rebelOrigem.getInventory().setAmmo(rebelOrigem.getInventory().getAmmo() - inventarioOrigem.getAmmo());
            reberDestino.getInventory().setAmmo(reberDestino.getInventory().getAmmo() + inventarioOrigem.getAmmo());

        }
        if (rebelOrigem.getInventory().getFood() > 0) {

            rebelOrigem.getInventory().setFood(rebelOrigem.getInventory().getFood() - inventarioOrigem.getFood());
            reberDestino.getInventory().setFood(reberDestino.getInventory().getFood() + inventarioOrigem.getFood());

        }
        if (rebelOrigem.getInventory().getWater() > 0) {

            rebelOrigem.getInventory().setWater(rebelOrigem.getInventory().getWater() - inventarioOrigem.getWater());
            reberDestino.getInventory().setWater(reberDestino.getInventory().getWater() + inventarioOrigem.getWater());

        }
    }
}
