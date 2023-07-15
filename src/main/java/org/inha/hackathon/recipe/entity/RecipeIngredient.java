package org.inha.hackathon.recipe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.inha.hackathon.refrigerator.entity.IngredientMeta;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name = "ingredient_meta_id")
    private IngredientMeta ingredientMeta;
}
