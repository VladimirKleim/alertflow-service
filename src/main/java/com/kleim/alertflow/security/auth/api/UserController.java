package com.kleim.alertflow.security.auth.api;

import com.kleim.alertflow.security.auth.domain.*;
import com.kleim.alertflow.security.token.JwtTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final RegistrationService registrationService;
    private final UserDtoConverter dtoConverter;
    private final AuthenticationService authenticationService;

    private final UserService userService;


    public UserController(RegistrationService registrationService, UserDtoConverter dtoConverter, AuthenticationService authenticationService, UserService userService) {
        this.registrationService = registrationService;
        this.dtoConverter = dtoConverter;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> singUpRequest(
            @RequestBody UserSignUpRequest signUpRequest
    ) {
        log.info("Got request to sign up user: id({}), login({})", signUpRequest.id(), signUpRequest.login());
        var createdUser = registrationService.createUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoConverter.toDto(createdUser));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtTokenResponse> signInRequest(
            @RequestBody UserSignInRequest signInUser
    ) {
        log.info("Got request to authenticate user with login: {}", signInUser.login());
        var token = authenticationService.authenticateUser(signInUser);
     return ResponseEntity.status(HttpStatus.OK).body(new JwtTokenResponse(token));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Got request to get all users.");
        var gotUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(gotUsers.stream()
                        .map(dtoConverter::toDto)
                        .toList());
    }
}

