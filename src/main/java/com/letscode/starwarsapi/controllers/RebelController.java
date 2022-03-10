package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.services.RebelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rebels")
public class RebelController {

    private final RebelService service;

    public RebelController(RebelService service) {
        this.service = service;
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
