package org.inha.hackathon.refrigerator.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.inha.hackathon.refrigerator.entity.Ingredient;

import java.time.LocalDateTime;

@Getter
public class ExpirationIngredientResponseDto {
    private Long id;
    private String name;
    private Integer expirationDate;

    @Builder
    public ExpirationIngredientResponseDto(Long id, String name, Integer expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public static ExpirationIngredientResponseDto of(Ingredient ingredient, Integer expireDate) {
        return ExpirationIngredientResponseDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getIngredientMeta().getName())
                .expirationDate(expireDate)
                .build();
    }
}
