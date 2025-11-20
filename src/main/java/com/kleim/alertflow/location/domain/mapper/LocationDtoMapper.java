package com.kleim.alertflow.location.domain.mapper;

import com.kleim.alertflow.location.domain.Location;
import com.kleim.alertflow.location.domain.LocationDto;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoMapper {

    public LocationDto toDto(Location location) {
        return new LocationDto(
                location.id(),
                location.name(),
                location.address(),
                location.workers(),
                location.description()
        );
    }
}
