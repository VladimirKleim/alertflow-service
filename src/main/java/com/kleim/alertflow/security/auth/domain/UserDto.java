package com.kleim.alertflow.security.auth.domain;

public record UserDto(
        Long id,
        String login,
        UserDepartment department,
        UserRole role
) {
}
