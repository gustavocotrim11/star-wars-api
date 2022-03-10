package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.dtos.RebelDto;
import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import com.letscode.starwarsapi.services.RebelService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rebels")
public class RebelController {

    private final RebelService service;

    public RebelController(RebelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RebelModel> createRebel(@RequestBody RebelDto rebelDto) {
        LocalizationModel localizationModel = new LocalizationModel(
                rebelDto.getLocalization().getLatitude(),
                rebelDto.getLocalization().getLongitude(),
                rebelDto.getLocalization().getBaseName()
        );

        InventoryModel inventoryModel = new InventoryModel(
                rebelDto.getInventory().getWeapon(),
                rebelDto.getInventory().getAmmo(),
                rebelDto.getInventory().getWater(),
                rebelDto.getInventory().getFood()
        );

        RebelModel rebelModel = new RebelModel(
                rebelDto.getName(),
                rebelDto.getAge(),
                GenderEnum.valueOf(rebelDto.getGender().toUpperCase()),
                localizationModel,
                inventoryModel
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(rebelModel));
    }

    @GetMapping
    public ResponseEntity<List<RebelModel>> findAll() {
        List<RebelModel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RebelModel> findById(@PathVariable UUID id) {
        RebelModel obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
