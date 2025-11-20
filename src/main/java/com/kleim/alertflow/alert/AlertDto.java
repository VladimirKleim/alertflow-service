package com.kleim.alertflow.alert;


import com.kleim.alertflow.alert.domain.AlertComment;

import java.time.OffsetDateTime;
import java.util.List;

public record AlertDto(
        Long id,
        String name,
        String description,
        List<AlertComment> comment,
        OffsetDateTime createDate,
        OffsetDateTime closeDate,
        Long analystId,
        Long locationId,
        AlertCategory category,
        AlertStatus status
) {

}
