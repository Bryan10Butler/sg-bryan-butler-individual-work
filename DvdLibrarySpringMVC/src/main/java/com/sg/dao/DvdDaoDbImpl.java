package com.sg.dao;

import com.sg.model.Dvd;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


//prepared statements
public class DvdDaoDbImpl implements DvdDao {

    private static final String SQL_INSERT_DVD
            = "insert into Dvd "
            + "(title, releaseLocalDate, directorName, ratingMppa) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL_DVDS
            = "select * from Dvd";

    private static final String SQL_DELETE_DVD
            = "delete from Dvd where dvdId = ?";

    private static final String SQL_UPDATE_DVD
            = "update Dvd set "
            + "title = ?, releaseLocalDate = ?, directorName = ?, ratingMppa = ? "
            + "where dvdId = ?";

    private static final String SQL_SELECT_DVD
            = "select * from Dvd where dvdId = ?";

//Jdbc Template - execute statements against the database

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dvd getDvd(Long DvdId) {

        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,
                    new DvdMapper(), DvdId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just
            // want to return null in this case
            return null;
        }
    }

    //getting row back from the db and use query gets a list of things back (expect more then one thing back
    //contact mapper takes a row and turns into a dto
    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS,
                new DvdMapper());
    }

    @Override
    public void removeDvd(Long dvdId) {

        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);

    }

    @Override
    public void editDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                Date.valueOf(dvd.getReleaseLocalDate()),
                dvd.getDirectorName(),
                dvd.getRatingMppa(),
                //fills in where clause for us
                dvd.getDvdId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        //call prepared statements
        jdbcTemplate.update(SQL_INSERT_DVD,
                //the below populates the placeholders in the prepared statement
                dvd.getTitle(),
                Date.valueOf(dvd.getReleaseLocalDate()),
                dvd.getDirectorName(),
                dvd.getRatingMppa());
                //dvd.getNotes());

        //query for object expects one object
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        dvd.setDvdId(newId);
        return dvd;
    }

    private static final class DvdMapper implements RowMapper<Dvd> {
        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getLong("dvdId"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseLocalDate(rs.getTimestamp("releaseLocalDate").toLocalDateTime().toLocalDate());
            dvd.setDirectorName(rs.getString("directorName"));
            dvd.setRatingMppa(rs.getString("ratingMppa"));
            //dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }



}
