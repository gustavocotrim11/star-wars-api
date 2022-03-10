package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.services.LocalizationService;
import com.letscode.starwarsapi.services.RebelService;
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
