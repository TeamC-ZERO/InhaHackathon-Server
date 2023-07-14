package org.inha.hackathon.refrigerator.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;
    @OneToOne
    @JoinColumn(name = "ingredient_meta_id")
    private IngredientMeta ingredientMeta;
    private LocalDateTime purchaseDate;
    private LocalDateTime endDate;
}
