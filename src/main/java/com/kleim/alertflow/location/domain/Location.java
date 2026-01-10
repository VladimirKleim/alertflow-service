package com.kleim.alertflow.location.domain;

public record Location(
        Long id,
        String name,
        String address,
        Integer workers,
        String description
) {
}
