package com.kleim.alertflow.location.domain;

public record CreateLocationRequest(
      Long id,
      String name,
      String address,
      Integer workers,
      String description

) {
}
