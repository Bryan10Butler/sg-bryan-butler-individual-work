package com.sg.dao;

import com.sg.model.Dvd;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class DvdDaoTest {

    private DvdDao dao;

    public DvdDaoTest() {

    }


    @Before
    public void setUp() {
        // ask Spring for our DAO
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        dao = ctx.getBean("DvdDao", DvdDao.class);

        // remove all of the Contacts
        List<Dvd> dvd = dao.getAllDvds();
        for (Dvd currentDvd : dvd) {
            dao.removeDvd(currentDvd.getDvdId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AddGetDvdById() {

        Dvd dvd = new Dvd();

        dvd.setTitle("Brink");
        dvd.setReleaseLocalDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        dvd.setDirectorName("Bryan Butler");
        dvd.setRatingMppa("R");
        dao.addDvd(dvd);

        Dvd fromDao = dao.getDvd(dvd.getDvdId());

        assertEquals(fromDao, dvd);
    }

    @Test
    public void getAllDvds() {

        Dvd dvd = new Dvd();

        dvd.setTitle("Brink");
        dvd.setReleaseLocalDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        dvd.setDirectorName("Bryan Butler");
        dvd.setRatingMppa("R");
        dao.addDvd(dvd);

        dvd.setTitle("Lion King");
        dvd.setReleaseLocalDate(LocalDate.parse("2012-01-01", DateTimeFormatter.ISO_DATE));
        dvd.setDirectorName("Bryan Butler");
        dvd.setRatingMppa("R");
        dao.addDvd(dvd);

        List<Dvd> dList = dao.getAllDvds();
        assertEquals(dList.size(), 2);
    }

    @Test
    public void removeDvd() {

        Dvd dvd = new Dvd();

        dvd.setTitle("Brink");
        dvd.setReleaseLocalDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        dvd.setDirectorName("Bryan Butler");
        dvd.setRatingMppa("R");
        dao.addDvd(dvd);
        Dvd fromDao = dao.getDvd(dvd.getDvdId());
        assertEquals(fromDao, dvd);
        dao.removeDvd(dvd.getDvdId());
        assertNull(dao.getDvd(dvd.getDvdId()));
    }

    @Test
    public void editDvd() {

        Dvd dvd = new Dvd();

        dvd.setTitle("Brink");
        dvd.setReleaseLocalDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        dvd.setDirectorName("Bryan Butler");
        dvd.setRatingMppa("R");
        dao.addDvd(dvd);
        dvd.setTitle("Titanic");
        dao.editDvd(dvd);
        Dvd fromDb = dao.getDvd(dvd.getDvdId());
        assertEquals(fromDb, dvd);
    }
}