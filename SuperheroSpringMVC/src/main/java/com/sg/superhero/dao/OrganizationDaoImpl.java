package com.sg.superhero.dao;

import com.sg.dao.OrganizationDao;
import com.sg.dto.Hero;
import com.sg.dto.Location;
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

public class OrganizationDaoImpl implements OrganizationDao{

    JdbcTemplate jdbcTemplate;

    static String SQL_CREATE = "INSERT INTO Organization (`Name`, Description, LocationID) " +
            "VALUES (?,?,?)";
    static String SQL_RETRIEVE_ALL = "SELECT * FROM Organization LIMIT ? OFFSET ?";
    static String SQL_UPDATE = "UPDATE Organization " +
            "SET Name = ?, Description = ?, LocationID = ? " +
            "WHERE OrganizationID = ?";
    static String SQL_DELETE = "DELETE FROM Organization " +
            "WHERE OrganizationID = ?";
    static String SQL_RETRIEVE_ORGANIZATION = "SELECT * FROM Organization " +
            "WHERE OrganizationID = ?";
    static String SQL_RETRIEVE_ORGANIZATION_BY_HERO = "SELECT * FROM Organization o " +
            "INNER JOIN HeroOrganization ho on o.OrganizationID = ho.OrganizationID " +
            "WHERE ho.HeroID = ? LIMIT ? OFFSET ?";

    //constructor
    @Inject
    public OrganizationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Organization addOrganization(Organization organization) {

        Long LocationID = null;
        if (organization.getLocation()!=null) {
            LocationID = organization.getLocation().getLocationId();
        }

        jdbcTemplate.update(SQL_CREATE,
                organization.getName(),
                organization.getDescription(),
                LocationID);

        long createID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        organization.setOrganizationId(createID);

        return organization;
    }

    @Override
    public void removeOrganization(Organization organization) {

        jdbcTemplate.update(SQL_DELETE, organization.getOrganizationId());
    }

    @Override
    public void updateOrganization(Organization organization) {

        Long LocationID = null;
        if (organization.getLocation()!=null) {
            LocationID = organization.getLocation().getLocationId();
        }

        jdbcTemplate.update(SQL_UPDATE,
                organization.getName(),
                organization.getDescription(),
                LocationID,
                organization.getOrganizationId());

    }

    @Override
    public List<Organization> retrieveAllOrganizations(int Limit, int Offset) {

        return jdbcTemplate.query(SQL_RETRIEVE_ALL,
                new OrganizationMapper(),
                Limit,
                Offset);
    }

    @Override
    public Organization retrieveOrganization(Long OrganizationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_ORGANIZATION, new OrganizationMapper(), OrganizationID);
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }

    @Override
    public List<Organization> retrieveOrganizationByHero(Hero hero, int Limit, int Offset) {

        return jdbcTemplate.query(SQL_RETRIEVE_ORGANIZATION_BY_HERO,
                new OrganizationMapper(),
                hero.getHeroId(),
                Limit,
                Offset);
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet resultSet, int i) throws SQLException {

            Organization organization = new Organization();

            organization.setOrganizationId(resultSet.getLong("OrganizationID"));
            organization.setName(resultSet.getString("Name"));
            organization.setDescription(resultSet.getString("Description"));
            Long LocationID = resultSet.getLong("LocationID");

            //lazy loading
            if(LocationID != null) {
                Location location = new Location();
                location.setLocationId(LocationID);
                organization.setLocation(location);
            }
            return organization;
        }
    }

}
