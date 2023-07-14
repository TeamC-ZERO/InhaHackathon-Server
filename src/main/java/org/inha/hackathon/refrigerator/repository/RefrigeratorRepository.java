package org.inha.hackathon.refrigerator.repository;

import org.inha.hackathon.refrigerator.entity.Ingredient;
import org.inha.hackathon.refrigerator.entity.IngredientMeta;
import org.inha.hackathon.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository<Ingredient, Long> {
    @Query("select r from Refrigerator r where r.user.id = :userId")
    Optional<Refrigerator> findRefrigerator(@Param("userId") Long userId);

    @Query("select i from Ingredient i join fetch i.ingredientMeta im where i.refrigerator.id = :refrigeratorId")
    List<Ingredient> findIngredients(@Param("refrigeratorId") Long refrigeratorId);
}
