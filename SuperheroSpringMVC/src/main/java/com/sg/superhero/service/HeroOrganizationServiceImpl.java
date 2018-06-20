package com.sg.superhero.service;

import com.sg.dao.HeroOrganizationDao;
import com.sg.dto.HeroOrganization;
import com.sg.service.HeroOrganizationService;

import javax.inject.Inject;
import java.util.List;

public class HeroOrganizationServiceImpl implements HeroOrganizationService{

    HeroOrganizationDao heroOrganizationDao;
    @Inject
    public HeroOrganizationServiceImpl(HeroOrganizationDao heroOrganizationDao) {
        this.heroOrganizationDao = heroOrganizationDao;
    }

    @Override
    public HeroOrganization addHeroOrganization(HeroOrganization heroOrganization) {
        return heroOrganizationDao.addHeroOrganization(heroOrganization);
    }

    @Override
    public void removeHeroOrganization(HeroOrganization heroOrganization) {
        heroOrganizationDao.removeHeroOrganization(heroOrganization);
    }

    @Override
    public void updateHeroOrganization(HeroOrganization heroOrganization) {
        heroOrganizationDao.updateHeroOrganization(heroOrganization);
    }

    @Override
    public HeroOrganization retrieveHeroOrganizationById(Long HeroOrganizationID) {
        return heroOrganizationDao.retrieveHeroOrganizationById(HeroOrganizationID);
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByOrganization(Long HeroOrganizationID) {
        return heroOrganizationDao.retrieveHeroOrganizationByOrganization(HeroOrganizationID);
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByHero(Long HeroOrganizationID) {
        return heroOrganizationDao.retrieveHeroOrganizationByHero(HeroOrganizationID);
    }
}
