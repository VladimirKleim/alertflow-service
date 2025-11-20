package com.kleim.alertflow.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.nio.file.AccessDeniedException;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ServerErrorDto> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        log.warn("Got Illegal arg exception: {}", exception.getMessage());
        var error = new ServerErrorDto(
                "Catch illegal arg exception",
                exception.getMessage(),
                OffsetDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ServerErrorDto> handleNotValidArgumentException(
            MethodArgumentNotValidException exception
    ) {
        log.warn("Validation failed: {}", exception.getMessage());
        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + " " + e.getDefaultMessage())
                .collect(Collectors.joining(" "));

        var error = new ServerErrorDto(
                "Not valid result",
                message,
                OffsetDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ServerErrorDto> handleNoSuchElementException(
            NoSuchElementException exception
    ) {
        log.warn("Got exception: No such Element exception");
        var error = new ServerErrorDto(
                "Catch exception: no such element",
                exception.getMessage(),
                OffsetDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ServerErrorDto> handleBadCredentialException(
            BadCredentialsException exception
    ) {
        log.warn("Unauthorized exception");
        var error = new ServerErrorDto(
                "Exception: unauthorized",
                exception.getMessage(),
                OffsetDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ServerErrorDto> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("Access denied: {}", e.getMessage());
        var error = new ServerErrorDto(
                "Access denied",
                "Insufficient permissions: " + e.getMessage(),
                OffsetDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ServerErrorDto> handleGlobalExceptionException(
            Exception exception
    ) {
        log.warn("Got server error");
        var error = new ServerErrorDto(
                "Error side's server",
                exception.getMessage(),
                OffsetDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}