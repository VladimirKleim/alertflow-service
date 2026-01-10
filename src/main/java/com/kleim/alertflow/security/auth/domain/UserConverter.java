package com.kleim.alertflow.security.auth.domain;

import com.kleim.alertflow.security.auth.repository.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.id(),
                user.login(),
                user.password(),
                user.department(),
                user.role()
        );
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getDepartment(),
                userEntity.getRole()
        );
    }
}
