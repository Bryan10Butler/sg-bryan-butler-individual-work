package com.sg.superhero.service;

import com.sg.dao.OrganizationDao;
import com.sg.dto.Hero;
import com.sg.dto.Organization;
import com.sg.service.OrganizationService;

import javax.inject.Inject;
import java.util.List;

public class OrganizationServiceImpl implements OrganizationService{

    OrganizationDao organizationDao;
    @Inject
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public Organization addOrganization(Organization organization) {
        return organizationDao.addOrganization(organization);
    }

    @Override
    public void removeOrganization(Organization organization) {
        organizationDao.removeOrganization(organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }

    @Override
    public List<Organization> retrieveAllOrganizations(int Limit, int Offset) {
        return organizationDao.retrieveAllOrganizations(Limit, Offset);
    }

    @Override
    public Organization retrieveOrganization(Long OrganizationID) {
        return organizationDao.retrieveOrganization(OrganizationID);
    }

    @Override
    public List<Organization> retrieveOrganizationByHero(Hero hero, int Limit, int Offset) {
        return organizationDao.retrieveOrganizationByHero(hero, Limit, Offset);
    }
}
