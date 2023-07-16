package org.inha.hackathon.recipe.repository;

import org.inha.hackathon.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("select r from Recipe r join fetch r.recipeIngredients join fetch r.details")
    Optional<Recipe> findRecipeDetail(Long id);
}
