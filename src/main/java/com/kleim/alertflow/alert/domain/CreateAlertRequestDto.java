package com.kleim.alertflow.alert.domain;

import com.kleim.alertflow.alert.AlertCategory;
import com.kleim.alertflow.alert.AlertStatus;
import com.kleim.alertflow.alert.db.AlertCommentEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.List;

public record CreateAlertRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        Long locationId,
        @NotNull
        AlertCategory category
) {
}