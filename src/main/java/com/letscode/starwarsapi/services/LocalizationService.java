package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.services.exceptions.LocalizationNotFoundException;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.repositories.LocalizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocalizationService {

    private final LocalizationRepository repository;

    public LocalizationService(LocalizationRepository repository) {
        this.repository = repository;
    }

    public LocalizationModel updateLocalization(LocalizationModel newLocalization ){
                    return repository.save(newLocalization);
    }

}
