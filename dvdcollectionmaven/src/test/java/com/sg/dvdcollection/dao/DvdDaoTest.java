package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Dvd;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


public class DvdDaoTest {

    private DvdDao dao = new DvdDaoFileImpl();


    public DvdDaoTest() {
    }


    @Before
    public void setUp() throws Exception {

        //Setup for good known state
        //Remove all dvd to start with clean state
        List<Dvd> DvdList = dao.getAllDvds();
        for (Dvd dvd : DvdList) {
            dao.removeDvd((dvd.getTitle()));
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddGetDvd() throws DvdPersistenceException {

        //create new dvd object
        Dvd dvd = new Dvd("Golf", LocalDate.parse("1990-01-01"),"R", "Bryan", "Pixar", "Five");

        //pass dvd into DAO
        dao.addDvd(dvd.getTitle(), dvd);

        //declare another dvd object
        //associate with dvd title golf
        //get dvd out of DAO
        Dvd fromDao = dao.getDvd(dvd.getTitle());

        //user assert equals
        //note these are two different memory locations
        //equals and hash saying we compare at values and not memory
        assertEquals(dvd, fromDao);
    }

    @Test
    public void testGetAllDvds() throws DvdPersistenceException {

        //create dvd 1
        Dvd dvd = new Dvd("Bryan", LocalDate.parse("1990-01-01"), "Bryan", "Bryan", "Bryan", "Bryan");

        //add dvd 1 to DAO
        dao.addDvd(dvd.getTitle(), dvd);

        //create dvd 2
        Dvd dvd2 = new Dvd("Butler", LocalDate.parse("1990-01-01"), "Butler" +
                "Butler", "Butler", "Butler", "Butler");

        //add dvd 2 to DAO
        dao.addDvd(dvd.getTitle(), dvd2);

        //ask dao for both dvds
        //ask for size
        assertEquals(2, dao.getAllDvds().size());

    }

    @Test
    public void removeDvd() throws DvdPersistenceException {

        //create dvd 1
        Dvd dvd = new Dvd("Bryan", LocalDate.parse("1990-01-01"), "Bryan", "Bryan", "Bryan", "Bryan");

        //add dvd 1 to DAO
        dao.addDvd(dvd.getTitle(), dvd);

        //create dvd 2
        Dvd dvd2 = new Dvd("Butler", LocalDate.parse("1990-01-01"), "Butler" +
                "Butler", "Butler", "Butler", "Butler");

        //add dvd 2 to DAO
        dao.addDvd(dvd2.getTitle(), dvd2);


        dao.removeDvd(dvd.getTitle());
        assertEquals(1, dao.getAllDvds().size());
        //assert whatever answer we get back is null
        //because we are removing student 1
        assertNull(dao.getDvd(dvd.getTitle()));

        dao.removeDvd(dvd2.getTitle());
        assertEquals(0, dao.getAllDvds().size());
        assertNull(dao.getDvd(dvd2.getTitle()));


    }

    @Test
    public void editDvd() throws DvdPersistenceException {

        //act

        //add dvd
        Dvd newDvd = new Dvd("Golf", LocalDate.parse("1990-01-01"), "R",
                "Bryan", "Give", "five");

        //add dvd
        dao.addDvd(newDvd.getTitle(), newDvd);

        //edit dvd

    }
}