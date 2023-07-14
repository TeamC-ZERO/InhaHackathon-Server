package org.inha.hackathon.user.repository;

import org.inha.hackathon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDeviceToken(String deviceToken);
}
