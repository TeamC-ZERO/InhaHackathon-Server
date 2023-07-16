package org.inha.hackathon.recipe.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.inha.hackathon.recipe.entity.Recipe;
import org.inha.hackathon.recipe.entity.RecipeIngredient;

@Getter
@Setter
public class RecipeIngredientDto {
    private String name;
    private String imageUrl;

    @Builder
    public RecipeIngredientDto(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static RecipeIngredientDto of(RecipeIngredient recipeIngredient) {
        return RecipeIngredientDto.builder()
                .name(recipeIngredient.getIngredientMeta().getName())
                .imageUrl(recipeIngredient.getIngredientMeta().getImageUrl())
                .build();
    }
}
