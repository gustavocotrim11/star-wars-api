package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.repositories.LocalizationRepository;
import com.letscode.starwarsapi.repositories.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizationService {

    private final LocalizationRepository repository;

    public LocalizationService(LocalizationRepository repository) {
        this.repository = repository;
    }

}
