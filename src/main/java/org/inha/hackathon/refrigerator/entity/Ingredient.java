package org.inha.hackathon.refrigerator.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_meta_id")
    private IngredientMeta ingredientMeta;
    private LocalDateTime purchaseDate;
    private LocalDateTime endDate;

    @Builder
    public Ingredient(Refrigerator refrigerator, IngredientMeta ingredientMeta, LocalDateTime purchaseDate, LocalDateTime endDate) {
        this.refrigerator = refrigerator;
        this.ingredientMeta = ingredientMeta;
        this.purchaseDate = purchaseDate;
        this.endDate = endDate;
    }
}
