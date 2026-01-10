package com.kleim.alertflow.alert.domain.mapper;

import com.kleim.alertflow.alert.Alert;
import com.kleim.alertflow.alert.AlertDto;
import org.springframework.stereotype.Component;

@Component
public class AlertDtoMapper {

    public AlertDto toDto(Alert alert) {
        return new AlertDto(
                alert.getId(),
                alert.getName(),
                alert.getDescription(),
                alert.getComment(),
                alert.getCreateDate(),
                alert.getCloseDate(),
                alert.getAnalystId(),
                alert.getLocationId(),
                alert.getCategory(),
                alert.getStatus()
        );
    }


}
