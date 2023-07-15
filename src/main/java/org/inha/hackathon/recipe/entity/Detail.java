package org.inha.hackathon.recipe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    private String imageUrl;

    private String content;

    private Integer recipeOrder;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
