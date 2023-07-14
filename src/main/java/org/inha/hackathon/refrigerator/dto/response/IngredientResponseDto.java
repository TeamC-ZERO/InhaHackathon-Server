package org.inha.hackathon.refrigerator.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.inha.hackathon.refrigerator.entity.Ingredient;

@Getter
public class IngredientResponseDto {
    private Long id;
    private String name;

    @Builder
    public IngredientResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static IngredientResponseDto of(Ingredient ingredient) {
        return IngredientResponseDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getIngredientMeta().getName())
                .build();
    }
}
