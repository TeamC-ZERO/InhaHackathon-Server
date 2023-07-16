package org.inha.hackathon.recipe.dto.response;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailResponseDto {
    private String imageUrl;
    private String name;
    private List<RecipeIngredientDto> recipeIngredients = new ArrayList<>();

}
