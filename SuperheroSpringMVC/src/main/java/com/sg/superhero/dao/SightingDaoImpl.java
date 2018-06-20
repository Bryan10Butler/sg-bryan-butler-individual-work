package com.sg.superhero.dao;

import com.sg.dao.SightingDao;
import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SightingDaoImpl implements SightingDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO Sighting (Date, Description, LocationID) " +
            "VALUES (?,?,?)";
    static String SQL_RETRIEVE_ALL = "SELECT * FROM Sighting LIMIT ? OFFSET ?";
    static String SQL_UPDATE = "UPDATE Sighting " +
            "SET Date = ?, Description = ?, LocationID = ? " +
            "WHERE SightingID = ?";
    static String SQL_DELETE = "DELETE FROM Sighting " +
            "WHERE SightingID = ?";
    static String SQL_RETRIEVE_SIGHTING = "SELECT * FROM Sighting " +
            "WHERE SightingID = ?";
    static String RETRIEVE_SIGHTING_BY_HERO ="SELECT * FROM Sighting s " +
            "INNER JOIN HeroSighting hs on s.SightingID = hs.SightingID " +
            "WHERE hs.HeroID = ? LIMIT ? OFFSET ?";
    static String RETRIEVE_SIGHTING_BY_LOCATION = "SELECT * FROM Sighting " +
            "WHERE LocationID = ? LIMIT ? OFFSET ?";
    static String RETRIEVE_SIGHTING_BY_DATE = "SELECT * FROM Sighting " +
            "WHERE Date = ? LIMIT ? OFFSET ?";

    //constructor
    @Inject
    public SightingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sighting addSighting(Sighting sighting) {

        Long LocationID = null;
        if (sighting.getLocation()!=null) {
            LocationID = sighting.getLocation().getLocationId();
        }

        jdbcTemplate.update(SQL_CREATE,
                Date.valueOf(sighting.getDate()),
                sighting.getDescription(),
                LocationID);

        long createID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        sighting.setSightingId(createID);

        return sighting;
    }

    @Override
    public void removeSighting(Sighting sighting) {

        jdbcTemplate.update(SQL_DELETE, sighting.getSightingId());
    }

    @Override
    public void updateSighting(Sighting sighting) {

        Long LocationID = null;
        if (sighting.getLocation()!=null) {
            LocationID = sighting.getLocation().getLocationId();
        }

        jdbcTemplate.update(SQL_UPDATE,
                Date.valueOf(sighting.getDate()),
                sighting.getDescription(),
                LocationID,
                sighting.getSightingId());
    }

    @Override
    public List<Sighting> retrieveAllSightings(int Limit, int Offset) {
        return jdbcTemplate.query(SQL_RETRIEVE_ALL,
                new SightingMapper(),
                Limit,
                Offset);
    }

    @Override
    public Sighting retrieveSighting(Long SightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_SIGHTING, new SightingMapper(), SightingID);
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<Sighting> retrieveSightingByHero(Hero hero, int Limit, int Offset) {
        return jdbcTemplate.query(RETRIEVE_SIGHTING_BY_HERO,
                new SightingMapper(), hero.getHeroId(), Limit, Offset);
    }

    @Override
    public List<Sighting> retrieveSightingByLocation(Location location, int Limit, int Offset) {
        return jdbcTemplate.query(RETRIEVE_SIGHTING_BY_LOCATION,
                new SightingMapper(),
                location.getLocationId(),
                Limit,
                Offset);
    }

    @Override
    public List<Sighting> retrieveSightingByDate(LocalDate localDate, int Limit, int Offset) {
        return jdbcTemplate.query(RETRIEVE_SIGHTING_BY_DATE,
                new SightingMapper(),
                Date.valueOf(localDate),
                Limit,
                Offset);
    }

    private static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet resultSet, int i) throws SQLException {

            Sighting sighting = new Sighting();

            sighting.setSightingId(resultSet.getLong("SightingID"));
            sighting.setDate(resultSet.getDate("Date").toLocalDate());
            sighting.setDescription(resultSet.getString("Description"));
            Long LocationID = resultSet.getLong("LocationID");

            //lazy loading
            if(LocationID != null) {
                Location location = new Location();
                location.setLocationId(LocationID);
                sighting.setLocation(location);
            }
            return sighting;
        }
    }

}
