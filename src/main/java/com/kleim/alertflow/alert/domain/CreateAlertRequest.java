package com.kleim.alertflow.alert.domain;

import com.kleim.alertflow.alert.AlertCategory;

public record CreateAlertRequest(

        String name,
        String description,
        Long locationId,
        AlertCategory category

) {
}