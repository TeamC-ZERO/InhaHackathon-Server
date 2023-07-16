package org.inha.hackathon.user.service;

import lombok.RequiredArgsConstructor;
import org.inha.hackathon.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

}
