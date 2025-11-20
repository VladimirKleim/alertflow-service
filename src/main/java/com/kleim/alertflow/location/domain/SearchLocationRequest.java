package com.kleim.alertflow.location.domain;

public record SearchLocationRequest(
        String name,
        Integer minWorkers,
        Integer maxWorkers
) {
}
