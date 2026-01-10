package com.kleim.alertflow.security.auth.domain;

import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto toDto(User user) {
        return new UserDto(
                user.id(),
                user.login(),
                user.department(),
                user.role()
        );
    }

}
