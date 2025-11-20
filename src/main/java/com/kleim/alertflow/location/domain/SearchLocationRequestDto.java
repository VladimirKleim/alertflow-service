package com.kleim.alertflow.location.domain;

public record SearchLocationRequestDto(
        String name,
        Integer minWorkers,
        Integer maxWorkers
) {
}
