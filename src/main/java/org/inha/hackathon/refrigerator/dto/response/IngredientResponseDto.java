package org.inha.hackathon.refrigerator.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.inha.hackathon.refrigerator.entity.Ingredient;

@Getter
public class IngredientResponseDto {
    private Long id;
    private String name;
    private String imgUrl;

    @Builder
    public IngredientResponseDto(Long id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public static IngredientResponseDto of(Ingredient ingredient, String imageUrl) {
        return IngredientResponseDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getIngredientMeta().getName())
                .imgUrl(imageUrl)
                .build();
    }
}
