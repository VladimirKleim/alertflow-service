package com.kleim.alertflow.security.auth.domain;

public record UserSignInRequest(
        String login,
        String password
) {
}
