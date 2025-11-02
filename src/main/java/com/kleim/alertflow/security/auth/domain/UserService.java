package com.kleim.alertflow.security.auth.domain;

import com.kleim.alertflow.security.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(UserService.class);


    public UserService(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        log.info("Attempt to create new user, id:{}", user.id());
        var entity = userRepository.save(userConverter.toEntity(user));
        return userConverter.toDomain(entity);
    }

    public User getUserByLogin(String login) {
        return userConverter.toDomain(
           userRepository.findByLogin(login).orElseThrow(() ->
                 new IllegalArgumentException("No such user with login: %s".formatted(login))));
    }

    public boolean isUserExistByLogin(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::toDomain)
                .toList();
    }

}
