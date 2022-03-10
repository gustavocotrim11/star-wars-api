package com.letscode.starwarsapi.repositories;

import com.letscode.starwarsapi.models.RebelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RebelRepository extends JpaRepository<RebelModel, UUID> {
}