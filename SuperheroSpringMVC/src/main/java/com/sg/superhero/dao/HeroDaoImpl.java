package com.sg.superhero.dao;

import com.sg.dao.HeroDao;
import com.sg.dto.Hero;
import com.sg.dto.Organization;
import com.sg.dto.Power;
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

public class HeroDaoImpl implements HeroDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO Hero (Name, Description) " +
            "VALUES (?,?)";
    static String SQL_RETRIEVE_ALL = "SELECT * FROM Hero LIMIT ? OFFSET ?";
    static String SQL_UPDATE = "UPDATE Hero " +
            "SET Name = ?, Description = ? " +
            "WHERE HeroID = ?";
    static String SQL_DELETE = "DELETE FROM Hero " +
            "WHERE HeroID = ?";
    static String SQL_RETRIEVE_HERO = "SELECT * FROM Hero " +
            "WHERE HeroID = ?";
    static String SQL_RETRIEVE_HERO_BY_POWER = "SELECT * FROM Hero h " +
            "INNER JOIN HeroPower hp on h.HeroID = hp.HeroID " +
            "WHERE hp.PowerID = ? LIMIT ? OFFSET ?";
    static String SQL_RETRIEVE_HERO_BY_ORGANIZATION = "SELECT * FROM Hero h " +
            "INNER JOIN HeroOrganization ho on h.HeroID = ho.HeroID " +
            "WHERE ho.OrganizationID = ? LIMIT ? OFFSET ?";
    static String SQL_RETRIEVE_HERO_BY_SIGHTINGS = "SELECT * FROM Hero h " +
            "INNER JOIN HeroSighting hs on h.HeroID = hs.HeroID " +
            "WHERE hs.SightingID = ? LIMIT ? OFFSET ?";

    //constructor
    @Inject
    public HeroDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Hero addHero(Hero hero) {

        jdbcTemplate.update(SQL_CREATE,
                hero.getName(),
                hero.getDescription());

        long createID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        hero.setHeroId(createID);

        return hero;
    }

    @Override
    public void removeHero(Hero hero) {

        jdbcTemplate.update(SQL_DELETE,
                hero.getHeroId());
    }

    @Override
    public void updateHero(Hero hero) {

        jdbcTemplate.update(SQL_UPDATE,
                hero.getName(),
                hero.getDescription(),
                hero.getHeroId());
    }

    @Override
    public List<Hero> retrieveAllHero(int Limit, int Offset) {

        return jdbcTemplate.query(SQL_RETRIEVE_ALL,
                new HeroMapper(),
                Limit,
                Offset);
    }

    @Override
    public Hero retrieveHero(Long HeroID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_HERO, new HeroMapper(), HeroID);
        }catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<Hero> retrieveHeroByPower(Power power, int Limit, int Offset) {
        return jdbcTemplate.query(SQL_RETRIEVE_HERO_BY_POWER,
                new HeroMapper(),
                power.getPowerId(),
                Limit,
                Offset);
    }

    @Override
    public List<Hero> retrieveHeroBySighting(Sighting sighting, int Limit, int Offset) {
        return jdbcTemplate.query(SQL_RETRIEVE_HERO_BY_SIGHTINGS,
                new HeroMapper(),
                sighting.getSightingId(),
                Limit,
                Offset);
    }

    @Override
    public List<Hero> retrieveHeroByOrganization(Organization organization, int Limit, int Offset) {
        return jdbcTemplate.query(SQL_RETRIEVE_HERO_BY_ORGANIZATION,
                new HeroMapper(),
                organization.getOrganizationId(),
                Limit,
                Offset);
    }

    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet resultSet, int i) throws SQLException {

            Hero hero = new Hero();

            hero.setHeroId(resultSet.getLong("HeroID"));
            hero.setName(resultSet.getString("Name"));
            hero.setDescription(resultSet.getString("Description"));

            return hero;
        }
    }
}
