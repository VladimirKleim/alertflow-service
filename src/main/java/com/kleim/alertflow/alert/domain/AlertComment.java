package com.kleim.alertflow.alert.domain;

import com.kleim.alertflow.alert.db.AlertEntity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

public record AlertComment(

        Long id,

        String comment,

        OffsetDateTime createdAt,

        Long analystId,

        Long alertId
) {
}