package com.letscode.starwarsapi.repositories;

import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.RebelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocalizationRepository extends JpaRepository<LocalizationModel, UUID> {
}
