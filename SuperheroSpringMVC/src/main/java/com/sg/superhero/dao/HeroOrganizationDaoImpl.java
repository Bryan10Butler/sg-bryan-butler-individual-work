package com.sg.superhero.dao;

import com.sg.dao.HeroOrganizationDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroOrganization;
import com.sg.dto.Organization;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HeroOrganizationDaoImpl implements HeroOrganizationDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO HeroOrganization (HeroID, OrganizationID) " +
            "VALUES (?,?)";
    static String SQL_RETRIEVE_HEROORGANIZATION = "SELECT * FROM HeroOrganization " +
            "WHERE HeroOrganizationID = ?";
    static String SQL_UPDATE = "UPDATE HeroOrganization " +
            "SET HeroID = ?, OrganizationID = ? " +
            "WHERE HeroOrganizationID = ?";
    static String SQL_DELETE = "DELETE FROM HeroOrganization " +
            "WHERE HeroOrganizationID = ?";
    static String SQL_RETRIEVE_HERO_ORG_BY_ORG = "SELECT * FROM HeroOrganization " +
            "WHERE OrganizationID = ?";
    static String SQL_RETRIEVE_HERO_ORG_BY_HERO = "SELECT * FROM HeroOrganization " +
            "WHERE HeroID = ?";

    //constructor
    @Inject
    public HeroOrganizationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public HeroOrganization addHeroOrganization(HeroOrganization heroOrganization) {

        jdbcTemplate.update(SQL_CREATE,
                heroOrganization.getHero().getHeroId(),
                heroOrganization.getOrganization().getOrganizationId());

        long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        heroOrganization.setHeroOrganizationId(createdId);

        return heroOrganization;
    }

    @Override
    public void removeHeroOrganization(HeroOrganization heroOrganization) {

        jdbcTemplate.update(SQL_DELETE, heroOrganization.getHeroOrganizationId());
    }

    @Override
    public void updateHeroOrganization(HeroOrganization heroOrganization) {

        jdbcTemplate.update(SQL_UPDATE,
                heroOrganization.getHero().getHeroId(),
                heroOrganization.getOrganization().getOrganizationId(),
                heroOrganization.getHeroOrganizationId());
    }

    @Override
    public HeroOrganization retrieveHeroOrganizationById(Long HeroOrganizationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_HEROORGANIZATION, new HeroOrganizationMapper(), HeroOrganizationID);
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByOrganization(Long heroOrganizationId) {
        try{
            return jdbcTemplate.query(SQL_RETRIEVE_HERO_ORG_BY_ORG, new HeroOrganizationMapper(), heroOrganizationId);
        }catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByHero(Long heroOrganizationId) {
        try{
            return jdbcTemplate.query(SQL_RETRIEVE_HERO_ORG_BY_HERO, new HeroOrganizationMapper(), heroOrganizationId);
        }catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    private static final class HeroOrganizationMapper implements RowMapper<HeroOrganization> {
        @Override
        public HeroOrganization mapRow(ResultSet resultSet, int i) throws SQLException {

            HeroOrganization heroOrganization = new HeroOrganization();

            heroOrganization.setHeroOrganizationId(resultSet.getLong("HeroOrganizationID"));

            Long HeroID = resultSet.getLong("HeroID");

            if (HeroID != null) {
                Hero hero = new Hero();
                hero.setHeroId(HeroID);
                heroOrganization.setHero(hero);
            }

            Long OrganizationID = resultSet.getLong("OrganizationID");

            if (OrganizationID != null) {
                Organization organization = new Organization();
                organization.setOrganizationId(OrganizationID);
                heroOrganization.setOrganization(organization);
            }
            return heroOrganization;
        }
    }


}
