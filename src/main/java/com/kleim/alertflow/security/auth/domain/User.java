package com.kleim.alertflow.security.auth.domain;

public record User(
        Long id,
        String login,
        String password,
        UserDepartment department,
        UserRole role

) {
}
