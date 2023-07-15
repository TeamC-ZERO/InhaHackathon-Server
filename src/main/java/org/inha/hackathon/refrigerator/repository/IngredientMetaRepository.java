package org.inha.hackathon.refrigerator.repository;

import org.inha.hackathon.refrigerator.entity.IngredientMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientMetaRepository extends JpaRepository<IngredientMeta, Long> {
    Optional<IngredientMeta> findByName(String name);
}