package com.kleim.alertflow.alert.domain.mapper;

import com.kleim.alertflow.alert.AlertStatus;
import com.kleim.alertflow.alert.db.AlertEntity;
import com.kleim.alertflow.alert.domain.CreateAlertRequest;
import com.kleim.alertflow.alert.domain.CreateAlertRequestDto;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class CreateAlertMapper {

    public CreateAlertRequest toDomain(CreateAlertRequestDto createAlertRequestDto) {
        return new CreateAlertRequest(
                createAlertRequestDto.name(),
                createAlertRequestDto.description(),
                createAlertRequestDto.locationId(),
                createAlertRequestDto.category()
        );
    }

    public AlertEntity toCreate(Long locationId, Long analystId, CreateAlertRequest alertRequest) {
        return new AlertEntity(
                null,
                alertRequest.name(),
                alertRequest.description(),
                List.of(),
                OffsetDateTime.now(),
                null,
                analystId,
                locationId,
                alertRequest.category(),
                AlertStatus.WAIT_STARTED
        );
    }
}