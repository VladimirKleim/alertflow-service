package com.kleim.alertflow.location.domain;

public record UpdateLocationRequest(
        String name,
        String address,
        Integer workers,
        String description
) {
}
