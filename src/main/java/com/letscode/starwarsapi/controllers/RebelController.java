package com.letscode.starwarsapi.controllers;


import com.letscode.starwarsapi.dtos.LocalizationDto;
import com.letscode.starwarsapi.dtos.RebelDto;
import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import com.letscode.starwarsapi.services.LocalizationService;
import com.letscode.starwarsapi.services.RebelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rebels")
@Api(value = "Rebels API REST")
public class RebelController {

    private final RebelService service;

    private final LocalizationService localizationService;

    public RebelController(RebelService service, LocalizationService localizationService) {
        this.service = service;
        this.localizationService = localizationService;
    }

    @ApiOperation(value = "Create a new Rebel with initial location and inventory")
    @PostMapping
    public ResponseEntity<RebelModel> createRebel(@RequestBody @Valid RebelDto rebelDto) {
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
                GenderEnum.fromString(rebelDto.getGender()).getGender(),
                localizationModel,
                inventoryModel
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(rebelModel));
    }

    @ApiOperation(value = "Get a list of all Rebels")
    @GetMapping
    public ResponseEntity<List<RebelModel>> findAll() {
        List<RebelModel> rebelList = service.findAll();
        return ResponseEntity.ok().body(rebelList);
    }

    @ApiOperation(value = "Get a Rebel by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<RebelModel> findById(@PathVariable UUID id) {
        RebelModel rebel = service.findById(id);
        return ResponseEntity.ok().body(rebel);
    }

    @ApiOperation(value = "Update a Rebel localization by id")
    @PutMapping( value = "/{id}/localization")
    public ResponseEntity<LocalizationModel> updateLocalization(@RequestBody @Valid LocalizationDto newLocalization, @PathVariable UUID id){
        RebelModel rebel = service.findById(id);

        LocalizationModel updatedLocalizationModel = rebel.getLocalization();
        updatedLocalizationModel.setLatitude(newLocalization.getLatitude());
        updatedLocalizationModel.setLongitude(newLocalization.getLongitude());
        updatedLocalizationModel.setBaseName(newLocalization.getBaseName());

        localizationService.updateLocalization(updatedLocalizationModel);
        return ResponseEntity.ok().body(updatedLocalizationModel);
    }

    @ApiOperation(value = "Report a Rebel as a traitor by id")
    @PutMapping(value = "/{accuserId}/report-traitor/{accusedId}")
    public ResponseEntity<RebelModel> reportTraitor(@PathVariable UUID accuserId, @PathVariable UUID accusedId){
        RebelModel AccuserRebel = service.findById(accuserId);
        RebelModel AccusedRebel = service.findById(accusedId);
        AccusedRebel.SetDenunciations();
        return ResponseEntity.ok().body(service.save(AccusedRebel));
    }

    @ApiOperation(value = "Delete a Rebel by id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRebel(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
