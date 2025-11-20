package com.kleim.alertflow.location.domain;

public record LocationDto(
        Long id,
        String name,
        String address,
        Integer workers,
        String description
) {
}
