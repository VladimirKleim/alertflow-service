package com.kleim.alertflow.security.auth.domain;

import com.kleim.alertflow.security.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    private final UserRepository userRepository;

    private final UserService userService;

    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public User createUser(UserSignUpRequest signUpRequest) {
        if (userRepository.existsByLogin(signUpRequest.login())) {
            throw new IllegalArgumentException("User with login %s already exist");
        }

        var hashPassword = passwordEncoder.encode(signUpRequest.password());
        var user = new User(
                null,
                signUpRequest.login(),
                hashPassword,
                UserDepartment.NOT_SPECIFIED,
                UserRole.GUEST
        );
        return userService.createUser(user);
    }
}
