package com.sg.superhero.dao;

import com.sg.dao.LocationDao;
import com.sg.dto.Location;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationDaoImpl implements LocationDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO Location (Name, Description, Street, City, State, Zip, Longitude, Latitude) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    static String SQL_RETRIEVE_ALL = "SELECT * FROM Location LIMIT ? OFFSET ?";
    static String SQL_UPDATE = "UPDATE Location " +
            "SET Name = ?, Description = ?, Street = ?, City = ?, State = ?, Zip = ?, Longitude = ?, Latitude = ? " +
            "WHERE LocationID = ?";
    static String SQL_DELETE = "DELETE FROM Location " +
            "WHERE LocationID = ?";
    static String SQL_RETRIEVE_LOCATION = "SELECT * FROM Location " +
            "WHERE LocationID = ?";

    //constructor
    @Inject
    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Location addLocation(Location location) {

        jdbcTemplate.update(SQL_CREATE,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLongitude(),
                location.getLatitude());

        long createID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        location.setLocationId(createID);

        return location;
    }

    @Override
    public void removeLocation(Location location) {

        jdbcTemplate.update(SQL_DELETE, location.getLocationId());

    }

    @Override
    public void updateLocation(Location location) {

        jdbcTemplate.update(SQL_UPDATE,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLongitude(),
                location.getLatitude(),
                location.getLocationId());
    }

    @Override
    public List<Location> retrieveAllLocations(int limit, int offset) {

        return jdbcTemplate.query(SQL_RETRIEVE_ALL,
                new LocationMapper(),
                limit,
                offset);
    }

    @Override
    public Location retrieveLocation(Long LocationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_LOCATION, new LocationMapper(), LocationID);
        }catch (EmptyResultDataAccessException ex) {}

        return null;
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet resultSet, int i) throws SQLException {

            Location location = new Location();

            location.setLocationId(resultSet.getLong("LocationID"));
            location.setName(resultSet.getString("Name"));
            location.setDescription(resultSet.getString("Description"));
            location.setStreet(resultSet.getString("Street"));
            location.setCity(resultSet.getString("City"));
            location.setState(resultSet.getString("State"));
            location.setZip(resultSet.getString("Zip"));
            location.setLongitude(resultSet.getString("Longitude"));
            location.setLatitude(resultSet.getString("Latitude"));

            return location;
        }
    }

}
