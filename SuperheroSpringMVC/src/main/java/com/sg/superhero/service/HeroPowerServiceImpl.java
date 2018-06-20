package com.sg.superhero.service;

import com.sg.dao.HeroPowerDao;
import com.sg.dto.HeroPower;
import com.sg.service.HeroPowerService;

import javax.inject.Inject;
import java.util.List;

public class HeroPowerServiceImpl implements HeroPowerService{

    HeroPowerDao heroPowerDao;
    @Inject
    public HeroPowerServiceImpl(HeroPowerDao heroPowerDao) {
        this.heroPowerDao = heroPowerDao;
    }

    @Override
    public HeroPower addHeroPower(HeroPower heroPower) {
        return heroPowerDao.addHeroPower(heroPower);
    }

    @Override
    public void removeHeroPower(HeroPower heroPower) {
        heroPowerDao.removeHeroPower(heroPower);
    }

    @Override
    public void updateHeroPower(HeroPower heroPower) {
        heroPowerDao.updateHeroPower(heroPower);
    }

    @Override
    public HeroPower retrieveHeroPowerById(Long HeroPowerID) {
        return heroPowerDao.retrieveHeroPowerById(HeroPowerID);
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByPower(Long HeroPowerID) {
        return heroPowerDao.retrieveHeroPowerByPower(HeroPowerID);
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByHero(Long HeroPowerID) {
        return heroPowerDao.retrieveHeroPowerByHero(HeroPowerID);
    }
}
