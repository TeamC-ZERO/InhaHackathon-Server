package org.inha.hackathon.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String deviceToken;
}
