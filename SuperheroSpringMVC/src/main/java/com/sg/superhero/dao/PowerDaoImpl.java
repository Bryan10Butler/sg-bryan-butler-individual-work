package com.sg.superhero.dao;

import com.sg.dao.PowerDao;
import com.sg.dto.Hero;
import com.sg.dto.Power;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PowerDaoImpl implements PowerDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO Power (`Name`, Description) " +
            "VALUES (?,?)";
    static String SQL_RETRIEVE_ALL = "SELECT * FROM Power LIMIT ? OFFSET ?";
    static String SQL_UPDATE = "UPDATE Power " +
            "SET Name = ?, Description = ? " +
            "WHERE PowerID = ?";
    static String SQL_DELETE = "DELETE FROM Power " +
            "WHERE PowerID = ?";
    static String SQL_RETRIEVE_POWER = "SELECT * FROM Power " +
            "WHERE PowerID = ?";
    static String SQL_RETRIEVE_POWER_BY_HERO = "SELECT * FROM Power p " +
            "INNER JOIN HeroPower hp on p.PowerID = hp.PowerID " +
            "WHERE hp.HeroID = ? LIMIT ? OFFSET ?";

    //constructor
    @Inject
    public PowerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Power addPower(Power power) {

        jdbcTemplate.update(SQL_CREATE,
                power.getName(),
                power.getDescription());

        long createID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        power.setPowerId(createID);

        return power;
    }

    @Override
    public void removePower(Power power) {

        jdbcTemplate.update(SQL_DELETE, power.getPowerId());
    }

    @Override
    public void updatePower(Power power) {

        jdbcTemplate.update(SQL_UPDATE,
                power.getName(),
                power.getDescription(),
                power.getPowerId());
    }

    @Override
    public List<Power> retrieveAllPowers(int Limit, int Offset) {

        return jdbcTemplate.query(SQL_RETRIEVE_ALL,
                new PowerMapper(),
                Limit,
                Offset);
    }

    @Override
    public Power retrievePower(Long PowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_POWER, new PowerMapper(), PowerID);
        } catch(EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<Power> retrievePowerByHero(Hero hero, int Limit, int Offset) {
        return jdbcTemplate.query(SQL_RETRIEVE_POWER_BY_HERO,
                new PowerMapper(),
                hero.getHeroId(),
                Limit,
                Offset);
    }

    private static final class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet resultSet, int i) throws SQLException {

            Power power = new Power();

            power.setPowerId(resultSet.getLong("PowerID"));
            power.setName(resultSet.getString("Name"));
            power.setDescription(resultSet.getString("Description"));

            return power;
        }
    }

}
