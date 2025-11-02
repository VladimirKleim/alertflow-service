package com.kleim.alertflow.security.auth.domain;

import com.kleim.alertflow.security.token.JwtTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenManager jwtTokenManager;

    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, JwtTokenManager jwtTokenManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenManager = jwtTokenManager;
    }

    public String authenticateUser(UserSignInRequest signInUser) {
        var user = userService.getUserByLogin(signInUser.login());
        if (!passwordEncoder.matches(signInUser.password(), user.password())) {
            throw new BadCredentialsException("Not valid password");
        }
        if (!userService.isUserExistByLogin(signInUser.login())) {
            throw new IllegalArgumentException("Bad credential");
        }
        return jwtTokenManager.generateToken(user);
    }

    public User getAuthenticatedUser() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        if (user == null) {
            throw new BadCredentialsException("Auth no present");
        }
        return (User) user.getPrincipal();

    }
}
