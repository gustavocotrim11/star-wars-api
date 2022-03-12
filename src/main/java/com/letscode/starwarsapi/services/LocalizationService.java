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

    public List<LocalizationModel> findAll() {
        return repository.findAll();
    }

    public LocalizationModel findById(UUID id) {
        Optional<LocalizationModel> obj = repository.findById(id);
        return obj.get();
    }

    public LocalizationModel updateLocalization(UUID id , LocalizationModel newLocalization ){
        return repository.findById(id)
                .map(localization -> {
                    localization.setBaseName(newLocalization.getBaseName());
                    localization.setLongitude(newLocalization.getLongitude());
                    localization.setLatitude(newLocalization.getLatitude());
                    return repository.save(localization);
                })
                .orElseThrow(()->new LocalizationNotFoundException(id));
    }

}
