package com.sg.superhero.service;

import com.sg.dao.HeroDao;
import com.sg.dto.Hero;
import com.sg.dto.Organization;
import com.sg.dto.Power;
import com.sg.dto.Sighting;
import com.sg.service.HeroService;

import javax.inject.Inject;
import java.util.List;

public class HeroServiceImpl implements HeroService{

    HeroDao heroDao;
    @Inject
    public HeroServiceImpl(HeroDao heroDao) {
        this.heroDao = heroDao;
    }

    @Override
    public Hero addHero(Hero hero) {
        return heroDao.addHero(hero);
    }

    @Override
    public void removeHero(Hero hero) {
        heroDao.removeHero(hero);
    }

    @Override
    public void updateHero(Hero hero) {
        heroDao.updateHero(hero);
    }

    @Override
    public List<Hero> retrieveAllHero(int Limit, int Offset) {
        return heroDao.retrieveAllHero(Limit, Offset);
    }

    @Override
    public Hero retrieveHero(Long HeroID) {
        return heroDao.retrieveHero(HeroID);
    }

    @Override
    public List<Hero> retrieveHeroByPower(Power power, int Limit, int Offset) {
        return heroDao.retrieveHeroByPower(power, Limit, Offset);
    }

    @Override
    public List<Hero> retrieveHeroBySighting(Sighting sighting, int Limit, int Offset) {
        return heroDao.retrieveHeroBySighting(sighting, Limit, Offset);
    }

    @Override
    public List<Hero> retrieveHeroByOrganization(Organization organization, int Limit, int Offset) {
        return heroDao.retrieveHeroByOrganization(organization, Limit, Offset);
    }
}
