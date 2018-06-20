package com.sg.superhero.service;

import com.sg.dao.SightingDao;
import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.service.SightingService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class SightingServiceImpl implements SightingService{

    SightingDao sightingDao;
    @Inject
    public SightingServiceImpl(SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        return sightingDao.addSighting(sighting);
    }

    @Override
    public void removeSighting(Sighting sighting) {
        sightingDao.removeSighting(sighting);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public List<Sighting> retrieveAllSightings(int Limit, int Offset) {
        return sightingDao.retrieveAllSightings(Limit, Offset);
    }

    @Override
    public Sighting retrieveSighting(Long SightingID) {
        return sightingDao.retrieveSighting(SightingID);
    }

    @Override
    public List<Sighting> retrieveSightingByHero(Hero hero, int Limit, int Offset) {
        return sightingDao.retrieveSightingByHero(hero, Limit, Offset);
    }

    @Override
    public List<Sighting> retrieveSightingByLocation(Location location, int Limit, int Offset) {
        return sightingDao.retrieveSightingByLocation(location, Limit, Offset);
    }

    @Override
    public List<Sighting> retrieveSightingByDate(LocalDate localDate, int Limit, int Offset) {
        return sightingDao.retrieveSightingByDate(localDate, Limit, Offset);
    }
}
