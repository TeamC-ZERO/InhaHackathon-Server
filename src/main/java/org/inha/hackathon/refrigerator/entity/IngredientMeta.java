package org.inha.hackathon.refrigerator.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class IngredientMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_meta_id")
    private Long id;
    private String name;
    private Integer preservationDate;
    private String imageUrl;
}
