package com.kleim.alertflow.location.domain;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record UpdateLocationRequestDto(
        @Size(max = 55)
        String name,
        @Size(max = 55)
        String address,
        @PositiveOrZero
        Integer workers,
        String description
) {
}
