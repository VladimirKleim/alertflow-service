package com.kleim.alertflow.location.api;

import com.kleim.alertflow.location.domain.*;
import com.kleim.alertflow.location.domain.mapper.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final Logger log = LoggerFactory.getLogger(LocationController.class);
    private final LocationServiceImpl locationService;
    private final LocationDtoMapper locationDtoMapper;
    private final SearchLocationMapper searchLocationMapper;
    private final CreateLocationMapper createLocationMapper;
    private final UpdateLocationMapper updateLocationMapper;

    public LocationController(LocationServiceImpl locationService, LocationDtoMapper locationDtoMapper, SearchLocationMapper searchLocationMapper, CreateLocationMapper createLocationMapper, UpdateLocationMapper updateLocationMapper) {
        this.locationService = locationService;
        this.locationDtoMapper = locationDtoMapper;
        this.searchLocationMapper = searchLocationMapper;
        this.createLocationMapper = createLocationMapper;
        this.updateLocationMapper = updateLocationMapper;
    }


    @PostMapping
    public ResponseEntity<LocationDto> createLocation(
            @RequestBody @Valid CreateLocationRequestDto locationRequest
    ) {
        log.info("Got request to create location: id({})", locationRequest.id());
        var location = locationService.createLocation(createLocationMapper.toDomain(locationRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(locationDtoMapper.toDto(location));
    }

    @PostMapping("/search")
    public ResponseEntity<List<LocationDto>> searchLocationFilter(
            @RequestBody SearchLocationRequestDto searchLocationRequest
    ) {
        log.info("Got request to search locations by filter");
        var search = locationService.getLocationByFilter(searchLocationMapper.toDomain(searchLocationRequest));
        return ResponseEntity.status(HttpStatus.OK)
                .body(search.stream()
                        .map(locationDtoMapper::toDto)
                        .toList());
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocation() {
        log.info("Got request to recieve all locations");
        var gotLocations = locationService.getLocations();
        return ResponseEntity.status(HttpStatus.OK)
                .body(gotLocations.stream()
                        .map(locationDtoMapper::toDto)
                        .toList());
    }


    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(
            @PathVariable("locationId") Long locationId
    ) {
        log.info("Got request to recieve location with id:{}", locationId);
        var location = locationService.getLocationById(locationId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationDtoMapper.toDto(location));
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable("locationId") Long locationId
    ) {
        log.info("Got request to delete location with id:{}", locationId);
        locationService.deleteLocationById(locationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<LocationDto> updateLocationById(
            @PathVariable("locationId") Long locationId,
            @RequestBody @Valid UpdateLocationRequestDto locationRequest
    ) {
        log.info("Got request to update location id:{}",locationId);
        var updatedLocation = locationService.updateLocation(locationId,updateLocationMapper.toDomain(locationRequest));
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationDtoMapper.toDto(updatedLocation));
    }


}
