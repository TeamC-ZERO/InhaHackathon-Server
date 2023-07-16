package org.inha.hackathon.refrigerator.repository;

import org.inha.hackathon.refrigerator.entity.Ingredient;
import org.inha.hackathon.refrigerator.entity.IngredientMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByIngredientMeta(IngredientMeta ingredientMeta);
}
