package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.repositories.RebelRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RebelService {

    private final RebelRepository repository;

    public RebelService(RebelRepository repository) {
        this.repository = repository;
    }

    public List<RebelModel> findAll() {
        return repository.findAll();
    }

    public RebelModel findById(UUID id) {
        Optional<RebelModel> obj = repository.findById(id);
        return obj.get();
    }

    @Transactional
    public RebelModel save(RebelModel rebelModel) {
        return repository.save(rebelModel);
    }
}
