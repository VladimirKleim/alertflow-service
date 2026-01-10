package com.kleim.alertflow.location.domain;

import jakarta.validation.constraints.*;

public record CreateLocationRequestDto(
        @Null
        Long id,
        @NotBlank
        @Size(max = 55, message = "Имя локации не может быть больше, чем 55 символов")
        String name,
        @NotBlank
        @Size(max = 55, message = "Указаный адресс не должен быть больше, чем 55 символов")
        String address,
        @PositiveOrZero
        Integer workers,
        String description
) {
}
