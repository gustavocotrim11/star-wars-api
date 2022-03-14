package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.repositories.RebelRepository;
import com.letscode.starwarsapi.services.exceptions.RebelNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            Optional<RebelModel> obj = repository.findById(id);
            return obj.orElseThrow(() -> new RebelNotFoundException(id));
        } catch (IllegalArgumentException e) {
            throw new RebelNotFoundException(id);
        }
    }

    @Transactional
    public RebelModel save(RebelModel rebelModel) {
        return repository.save(rebelModel);
    }

    @Transactional
    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RebelNotFoundException(id);
        }
    }

}
