package com.kleim.alertflow.location.domain.mapper;

import com.kleim.alertflow.location.domain.UpdateLocationRequest;
import com.kleim.alertflow.location.domain.UpdateLocationRequestDto;
import com.kleim.alertflow.location.repository.LocationEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateLocationMapper {

       public void toSaveEntity(UpdateLocationRequest updateLocationRequest, LocationEntity entity) {
           if (updateLocationRequest.name() != null) {
               entity.setName(updateLocationRequest.name());
           }
           if (updateLocationRequest.address() != null) {
               entity.setName(updateLocationRequest.address());
           }
           if (updateLocationRequest.workers() != null) {
               entity.setWorkers(updateLocationRequest.workers());
           }
           if (updateLocationRequest.description() != null) {
               entity.setDescription(updateLocationRequest.description());
           }
       }

    public UpdateLocationRequest toDomain(UpdateLocationRequestDto locationRequestDto) {
        return new UpdateLocationRequest(
                locationRequestDto.name(),
                locationRequestDto.address(),
                locationRequestDto.workers(),
                locationRequestDto.description()
        );
    }
}
