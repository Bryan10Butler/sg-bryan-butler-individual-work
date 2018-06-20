package com.sg.superhero.dao;

import com.sg.dao.HeroPowerDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroPower;
import com.sg.dto.Power;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sun.invoke.empty.Empty;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HeroPowerDaoImpl implements HeroPowerDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO HeroPower (HeroID, PowerID) " +
            "VALUES (?,?)";
    static String SQL_RETRIEVE_HEROPOWER = "SELECT * FROM HeroPower " +
            "WHERE HeroPowerID = ?";
    static String SQL_UPDATE = "UPDATE HeroPower " +
            "SET HeroID = ?, PowerID = ? " +
            "WHERE HeroPowerID = ?";
    static String SQL_DELETE = "DELETE FROM HeroPower " +
            "WHERE HeroPowerID = ?";
    static String SQL_RETRIEVE_HERO_POWER_BY_POWER = "SELECT * FROM HeroPower " +
            "WHERE PowerID = ? ";
    static String SQL_RETREIEVE_HERO_POWER_BY_HERO = "SELECT * FROM HeroPower " +
            "WHERE HeroID = ? ";

    //constructor
    @Inject
    public HeroPowerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public HeroPower addHeroPower(HeroPower heroPower) {

        Long HeroID = null;
        Long PowerID = null;

        if (heroPower.getHero() != null) {
            HeroID = heroPower.getHero().getHeroId();
        }

        if (heroPower.getPower() != null) {
            PowerID = heroPower.getPower().getPowerId();
        }

        jdbcTemplate.update(SQL_CREATE,
                HeroID,
                PowerID);

        long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        heroPower.setHeroPowerId(createdId);

        return heroPower;
    }

    @Override
    public void removeHeroPower(HeroPower heroPower) {
        jdbcTemplate.update(SQL_DELETE,
                heroPower.getHeroPowerId());
    }

    @Override
    public void updateHeroPower(HeroPower heroPower) {

        Long HeroID = null;
        Long PowerID = null;

        if (heroPower.getHero() != null) {
            HeroID = heroPower.getHero().getHeroId();
        }

        if (heroPower.getPower() != null) {
            PowerID = heroPower.getPower().getPowerId();
        }

        jdbcTemplate.update(SQL_UPDATE,
                HeroID,
                PowerID,
                heroPower.getHeroPowerId());
    }

    @Override
    public HeroPower retrieveHeroPowerById(Long HeroPowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_HEROPOWER, new HeroPowerMapper(), HeroPowerID);
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByPower(Long HeroPowerID) {
        try {
            return jdbcTemplate.query(SQL_RETRIEVE_HERO_POWER_BY_POWER, new HeroPowerMapper(), HeroPowerID);
        }catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByHero(Long HeroPowerID) {
        try {
            return jdbcTemplate.query(SQL_RETREIEVE_HERO_POWER_BY_HERO, new HeroPowerMapper(), HeroPowerID);
        }catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    private static final class HeroPowerMapper implements RowMapper<HeroPower> {
        @Override
        public HeroPower mapRow(ResultSet resultSet, int i) throws SQLException {

            HeroPower heroPower = new HeroPower();

            heroPower.setHeroPowerId(resultSet.getLong("HeroPowerID"));

            //Lazy Loading HeroID
            Long HeroID = resultSet.getLong("HeroID");

            if (HeroID != null) {
                Hero hero = new Hero();
                hero.setHeroId(HeroID);
                heroPower.setHero(hero);
            }

            //Lazy Loading PowerID
            Long PowerID = resultSet.getLong("PowerID");

            if(PowerID != null) {
                Power power = new Power();
                power.setPowerId(PowerID);
                heroPower.setPower(power);
            }
            return heroPower;
        }
    }

}
