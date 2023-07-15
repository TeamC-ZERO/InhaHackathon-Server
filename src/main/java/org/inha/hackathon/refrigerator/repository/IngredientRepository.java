package org.inha.hackathon.refrigerator.repository;

import org.inha.hackathon.refrigerator.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
