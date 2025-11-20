package com.kleim.alertflow.web;

import java.time.OffsetDateTime;

public record ServerErrorDto(
        String detail,
        String detailedMessage,
        OffsetDateTime date
) {
}
