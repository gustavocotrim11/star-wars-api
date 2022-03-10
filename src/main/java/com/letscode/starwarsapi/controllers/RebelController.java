package com.letscode.starwarsapi.controllers;


import com.letscode.starwarsapi.dtos.RebelDto;
import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import com.letscode.starwarsapi.services.LocalizationService;
import com.letscode.starwarsapi.services.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rebels")
public class RebelController {

    private final RebelService service;

    private final LocalizationService localizationService;

    public RebelController(RebelService service, LocalizationService localizationService) {
        this.service = service;
        this.localizationService = localizationService;
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

    @PutMapping( value = "/{id}/localization")
    public ResponseEntity<?> updateLocalization(@RequestBody LocalizationModel newLocalization, @PathVariable UUID id){
        Optional<LocalizationModel> updatedLocalization = localizationService.updateLocalization( id , newLocalization );
        return ResponseEntity.ok().body(updatedLocalization);
    }
}
