package com.kleim.alertflow.alert.domain.mapper;

import com.kleim.alertflow.alert.db.AlertEntity;
import com.kleim.alertflow.alert.domain.UpdateAlertRequest;
import com.kleim.alertflow.alert.domain.UpdateAlertRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UpdateAlertMapper {

    public UpdateAlertRequest toDomain(UpdateAlertRequestDto requestDto) {
        return new UpdateAlertRequest(
                requestDto.name(),
                requestDto.description(),
                requestDto.locationId(),
                requestDto.category()
        );
    }

    public void toUpdate(UpdateAlertRequest request, AlertEntity entity) {
        if (request.name() != null) {
            entity.setName(request.name());
        }
        if (request.description() != null) {
            entity.setDescription(request.description());
        }
        if (request.locationId() != null) {
            entity.setLocationId(request.locationId());
        }
        if (request.category() != null) {
            entity.setCategory(request.category());
        }
    }
}