package com.sg.superhero.service;

import com.sg.dao.PowerDao;
import com.sg.dto.Hero;
import com.sg.dto.Power;
import com.sg.service.PowerService;
import com.sg.superhero.dao.PowerDaoImpl;

import javax.inject.Inject;
import java.util.List;

public class PowerServiceImpl implements PowerService{

    PowerDao powerDao;
    @Inject
    public PowerServiceImpl(PowerDao powerDao) {
        this.powerDao = powerDao;
    }

    @Override
    public Power addPower(Power power) {
        return powerDao.addPower(power);
    }

    @Override
    public void removePower(Power power) {
        powerDao.removePower(power);
    }

    @Override
    public void updatePower(Power power) {
        powerDao.updatePower(power);
    }

    @Override
    public List<Power> retrieveAllPowers(int Limit, int Offset) {
        return powerDao.retrieveAllPowers(Limit, Offset);
    }

    @Override
    public Power retrievePower(Long PowerID) {
        return powerDao.retrievePower(PowerID);
    }

    @Override
    public List<Power> retrievePowerByHero(Hero hero, int Limit, int Offset) {
        return powerDao.retrievePowerByHero(hero, Limit, Offset);
    }
}
