package com.kleim.alertflow.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.ContentType;
import org.springframework.http.MediaType;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.time.OffsetDateTime;

@Component
public class AccessDeniedException implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public AccessDeniedException(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.access.AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {

        var message = new ServerErrorDto(
                "",
                accessDeniedException.getMessage(),
                OffsetDateTime.now()
        );
        String jsonMessage = objectMapper.writeValueAsString(message);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(403);
        response.getWriter().write(jsonMessage);
    }
}
