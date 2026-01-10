package com.kleim.alertflow.location.domain;

import java.util.List;

public interface LocationService {
    List<Location> getLocations();
    void deleteLocationById(Long locationId);
    Location createLocation(CreateLocationRequest locationRequest);
    Location getLocationById(Long locationId);
    Location updateLocation(Long locationId, UpdateLocationRequest locationRequest);
    List<Location> getLocationByFilter(SearchLocationRequest searchLocationRequest);
    boolean isLocationExists(Long locationId);
}
