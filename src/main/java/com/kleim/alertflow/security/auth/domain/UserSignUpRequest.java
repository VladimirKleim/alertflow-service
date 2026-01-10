package com.kleim.alertflow.security.auth.domain;

public record UserSignUpRequest(
        Long id,
        String login,
        String password,
        UserDepartment department,
        UserRole role
) {
}
