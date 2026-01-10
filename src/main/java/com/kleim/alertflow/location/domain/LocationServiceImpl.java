package com.kleim.alertflow.location.domain;

import com.kleim.alertflow.location.domain.mapper.*;
import com.kleim.alertflow.location.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);
    private final LocationRepository locationRepository;
    private final CreateLocationMapper locationMapper;
    private final UpdateLocationMapper updateLocationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, CreateLocationMapper locationMapper, UpdateLocationMapper updateLocationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.updateLocationMapper = updateLocationMapper;
    }

    @Override
    public List<Location> getLocations() {
        return locationRepository.findAll()
                .stream()
                .map(locationMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteLocationById(Long locationId) {
        var location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location do not exist"));
        locationRepository.delete(location);
    }

    @Override
    public Location createLocation(CreateLocationRequest locationRequest) {
        var entity = locationRepository.save(locationMapper.toSaveEntity(locationRequest));
        return locationMapper.toDomain(entity);
    }

    @Override
    public Location getLocationById(Long locationId) {
        return locationMapper.toDomain(locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location do not exist")));
    }

    @Override
    public Location updateLocation(Long locationId, UpdateLocationRequest locationRequest) {
        var locationEntity = locationRepository.findById(locationId).orElseThrow(() ->
                new IllegalArgumentException("No such location exist"));

        updateLocationMapper.toSaveEntity(locationRequest, locationEntity);
        var updatedLocation = locationRepository.save(locationEntity);
        return locationMapper.toDomain(updatedLocation);
    }

    @Override
    public List<Location> getLocationByFilter(SearchLocationRequest searchLocationRequest) {
        var location = locationRepository.searchLocation(
                searchLocationRequest.name(),
                searchLocationRequest.minWorkers(),
                searchLocationRequest.maxWorkers()
        );
        return location.stream()
                .map(locationMapper::toDomain)
                .toList();
    }

    public boolean isLocationExists(Long locationId) {
        return locationRepository.existsById(locationId);
    }
}