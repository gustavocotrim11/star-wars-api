package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.dtos.InventoryDto;
import com.letscode.starwarsapi.dtos.NegotiateDTO;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.ItemPointEnum;
import com.letscode.starwarsapi.services.InventoryService;
import com.letscode.starwarsapi.services.RebelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/negotiate")
@Api(value = "Report API REST")
public class NegotiateController {
    private final InventoryService inventoryService;
    private final RebelService rebelService;

    public NegotiateController(InventoryService inventoryService, RebelService rebelService) {
        this.inventoryService = inventoryService;
        this.rebelService = rebelService;
    }

    @ApiOperation(value = "Negotiate items ")
    @PostMapping
    public ResponseEntity<?> negotiateItems(@PathVariable UUID donorId, @PathVariable UUID receiverId,
                                            @RequestBody NegotiateDTO negotiateDTO){

        try {

            RebelModel rebel1 = rebelService.findById(donorId);
            RebelModel rebel2 = rebelService.findById(receiverId);

            //verificar se os IDs existem

            if (rebel1.getTraitor() || rebel2.getTraitor()){
                return new ResponseEntity<>("A traitor cannot trade resources with other rebels.", HttpStatus.BAD_REQUEST);
            }

            if (rebel1.getInventory() == null && negotiateDTO.getItemsToNegotiate1() == null) { return null;}

            var validateQuantity1 = validateQuantityOfItems(negotiateDTO.getItemsToNegotiate1(), rebel1);
            var validateQuantity2 = validateQuantityOfItems(negotiateDTO.getItemsToNegotiate2(), rebel2);

            var sumOfItemsToBeNegotiated1 = sumOfItemsToBeNegotiated(negotiateDTO.getItemsToNegotiate1());
            var sumOfItemsToBeNegotiated2 = sumOfItemsToBeNegotiated(negotiateDTO.getItemsToNegotiate2());

            if (sumOfItemsToBeNegotiated1 == sumOfItemsToBeNegotiated2) {

                changeItems(rebel1, rebel2,negotiateDTO.getItemsToNegotiate1());
                changeItems(rebel2, rebel1,negotiateDTO.getItemsToNegotiate2());

            }
            rebelService.save(rebel1);
            rebelService.save(rebel2);
            return new ResponseEntity<> ("Successful negotiation",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateQuantityOfItems(InventoryDto inventoryDto, RebelModel rebel) {
        return (rebel.getInventory().getWeapon() >= inventoryDto.getWeapon()) &&
                (rebel.getInventory().getAmmo() >= inventoryDto.getAmmo()) &&
                (rebel.getInventory().getFood() >= inventoryDto.getFood()) &&
                (rebel.getInventory().getWater() >= inventoryDto.getWater());
    }

    private int sumOfItemsToBeNegotiated (InventoryDto inventoryDto) {
        var sumOfItems =
                (inventoryDto.getWeapon() * ItemPointEnum.WEAPON.getPoint() + inventoryDto.getAmmo() * ItemPointEnum.AMMO.getPoint() + inventoryDto.getFood() * ItemPointEnum.FOOD.getPoint() + inventoryDto.getWater() * ItemPointEnum.WATER.getPoint());
        return sumOfItems;
    }

    private void changeItems(RebelModel rebelOrigem, RebelModel reberDestino,
                             InventoryDto inventarioOrigem){


        if (rebelOrigem.getInventory().getWeapon() > 0){

            rebelOrigem.getInventory().setWeapon( rebelOrigem.getInventory().getWeapon() - inventarioOrigem.getWeapon());
            reberDestino.getInventory().setWeapon( reberDestino.getInventory().getWeapon() + inventarioOrigem.getWeapon());

        }
        if (rebelOrigem.getInventory().getAmmo() > 0){

            rebelOrigem.getInventory().setAmmo( rebelOrigem.getInventory().getAmmo() - inventarioOrigem.getAmmo());
            reberDestino.getInventory().setAmmo( reberDestino.getInventory().getAmmo() + inventarioOrigem.getAmmo());

        }
        if (rebelOrigem.getInventory().getFood() > 0){

            rebelOrigem.getInventory().setFood( rebelOrigem.getInventory().getFood() - inventarioOrigem.getFood());
            reberDestino.getInventory().setFood( reberDestino.getInventory().getFood() + inventarioOrigem.getFood());

        }
        if (rebelOrigem.getInventory().getWater() > 0){

            rebelOrigem.getInventory().setWater( rebelOrigem.getInventory().getWater() - inventarioOrigem.getWater());
            reberDestino.getInventory().setWater( reberDestino.getInventory().getWater() + inventarioOrigem.getWater());

        }
    }


}
