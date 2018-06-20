package com.sg.superhero.service;

import com.sg.dao.LocationDao;
import com.sg.dto.Location;
import com.sg.service.LocationService;

import javax.inject.Inject;
import java.util.List;

public class LocationServiceImpl implements LocationService{

    LocationDao locationDao;
    @Inject
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Location addLocation(Location location) {
        return locationDao.addLocation(location);
    }

    @Override
    public void removeLocation(Location location) {
        locationDao.removeLocation(location);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    public List<Location> retrieveAllLocations(int Limit, int Offset) {
        return locationDao.retrieveAllLocations(Limit, Offset);
    }

    @Override
    public Location retrieveLocation(Long LocationID) {
        return locationDao.retrieveLocation(LocationID);
    }
}
