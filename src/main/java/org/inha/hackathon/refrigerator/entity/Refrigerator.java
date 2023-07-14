package org.inha.hackathon.refrigerator.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.inha.hackathon.user.entity.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Refrigerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrigerator_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;

}
