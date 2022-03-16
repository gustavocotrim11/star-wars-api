package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.repositories.InventoryRepository;
import com.letscode.starwarsapi.repositories.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

}
