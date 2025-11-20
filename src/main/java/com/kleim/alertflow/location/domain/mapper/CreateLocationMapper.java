package com.kleim.alertflow.location.domain.mapper;

import com.kleim.alertflow.location.domain.CreateLocationRequest;
import com.kleim.alertflow.location.domain.CreateLocationRequestDto;
import com.kleim.alertflow.location.domain.Location;
import com.kleim.alertflow.location.repository.LocationEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateLocationMapper {


    public LocationEntity toSaveEntity(CreateLocationRequest locationRequest) {
        return new LocationEntity(
           locationRequest.id(),
           locationRequest.name(),
           locationRequest.address(),
           locationRequest.workers(),
           locationRequest.description()
        );
    }

    public Location toDomain(LocationEntity location) {
        return new Location(
                location.getId(),
                location.getName(),
                location.getAddress(),
                location.getWorkers(),
                location.getDescription()
        );
    }

    public CreateLocationRequest toDomain(CreateLocationRequestDto createLocationRequestDto) {
        return new CreateLocationRequest(
                createLocationRequestDto.id(),
                createLocationRequestDto.name(),
                createLocationRequestDto.address(),
                createLocationRequestDto.workers(),
                createLocationRequestDto.description()
        );
    }
}
