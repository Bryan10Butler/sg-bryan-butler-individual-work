package com.sg.superhero.dao;

import com.sg.dao.HeroSightingDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Sighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HeroSightingDaoImpl implements HeroSightingDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO HeroSighting (HeroID, SightingID) " +
            "VALUES (?,?)";
    static String SQL_RETRIEVE_HEROSIGHTING = "SELECT * FROM HeroSighting " +
            "WHERE HeroSightingID = ?";
    static String SQL_UPDATE = "UPDATE HeroSighting " +
            "SET HeroID = ?, SightingID = ? " +
            "WHERE HeroSightingID = ?";
    static String SQL_DELETE = "DELETE FROM HeroSighting " +
            "WHERE HeroSightingID = ?";
    static String SQL_RETRIEVE_HERO_SIGHTING_BY_HERO = "SELECT * FROM HeroSighting " +
            "WHERE HeroID = ? ";
    static String SQL_RETRIEVE_HERO_SIGHTING_BY_SIGHTING = "SELECT * FROM HeroSighting " +
            "WHERE SightingID = ? ";

    //constructor
    @Inject
    public HeroSightingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public HeroSighting addHeroSighting(HeroSighting heroSighting) {

        jdbcTemplate.update(SQL_CREATE,
                heroSighting.getHero().getHeroId(),
                heroSighting.getSighting().getSightingId());

        long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        heroSighting.setHeroSightingId(createdId);

        return heroSighting;
    }

    @Override
    public void removeHeroSighting(HeroSighting heroSighting) {
        jdbcTemplate.update(SQL_DELETE, heroSighting.getHeroSightingId());
    }

    @Override
    public void updateHeroSighting(HeroSighting heroSighting) {

        jdbcTemplate.update(SQL_UPDATE,
                heroSighting.getHero().getHeroId(),
                heroSighting.getSighting().getSightingId(),
                heroSighting.getHeroSightingId());
    }

    @Override
    public HeroSighting retrieveHeroSightingById(Long HeroSightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_HEROSIGHTING,
                    new HeroSightingMapper(),
                    HeroSightingID);
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<HeroSighting> retrieveHeroSightingBySighting(Long sightingId) {
        try {
            return jdbcTemplate.query(SQL_RETRIEVE_HERO_SIGHTING_BY_SIGHTING,
                    new HeroSightingMapper(),
                    sightingId);
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<HeroSighting> retrieveHeroSightingByHero(Long heroId) {
        try {
            return jdbcTemplate.query(SQL_RETRIEVE_HERO_SIGHTING_BY_HERO,
                    new HeroSightingMapper(),
                    heroId);
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    private static final class HeroSightingMapper implements RowMapper<HeroSighting> {
        @Override
        public HeroSighting mapRow(ResultSet resultSet, int i) throws SQLException {

            HeroSighting heroSighting = new HeroSighting();

            heroSighting.setHeroSightingId(resultSet.getLong("HeroSightingID"));

            Long HeroID = resultSet.getLong("HeroID");

            if (HeroID != null) {
                Hero hero = new Hero();
                hero.setHeroId(HeroID);
                heroSighting.setHero(hero);
            }

            Long SightingID = resultSet.getLong("SightingID");

            if (SightingID != null) {
                Sighting sighting = new Sighting();
                sighting.setSightingId(SightingID);
                heroSighting.setSighting(sighting);
            }

            return heroSighting;
        }
    }

}
