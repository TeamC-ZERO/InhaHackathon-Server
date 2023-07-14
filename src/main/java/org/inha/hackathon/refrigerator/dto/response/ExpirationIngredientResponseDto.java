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

    public static ExpirationIngredientResponseDto of(Ingredient ingredient) {
        return ExpirationIngredientResponseDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getIngredientMeta().getName())
                .expirationDate(calculateExpirationDate(ingredient.getPurchaseDate(), ingredient.getIngredientMeta().getPreservationDate()))
                .build();
    }

    public static int calculateExpirationDate(LocalDateTime startDate, int period) {
        LocalDateTime now = LocalDateTime.now();
        int expirationDate = now.getYear() * 365 + now.getMonthValue() * 30 + now.getDayOfMonth();
        expirationDate -= startDate.getYear() * 365 + startDate.getMonthValue() * 30 + startDate.getDayOfMonth() + period;
        return expirationDate;
    }
}
