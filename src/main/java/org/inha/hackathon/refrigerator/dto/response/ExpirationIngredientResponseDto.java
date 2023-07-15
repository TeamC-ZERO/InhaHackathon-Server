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

    private String imgUrl;

    @Builder
    public ExpirationIngredientResponseDto(Long id, String name, Integer expirationDate, String imgUrl) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.imgUrl = imgUrl;
    }

    public static ExpirationIngredientResponseDto of(Ingredient ingredient, Integer expireDate, String imgUrl) {
        return ExpirationIngredientResponseDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getIngredientMeta().getName())
                .expirationDate(expireDate)
                .imgUrl(imgUrl)
                .build();
    }
}
