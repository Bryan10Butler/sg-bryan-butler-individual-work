package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoTaxesTest {


    FlooringDaoTaxes taxesDao;

    public FlooringDaoTaxesTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        taxesDao = ctx.getBean("taxesDao", FlooringDaoTaxes.class);
    }

    //Good known state
    @Before
    public void setUp() throws Exception {

        List<Tax> taxList = taxesDao.retrieveAllTaxes();

        for(Tax tempTax : taxList) {
            taxesDao.removeTax(tempTax);
        }
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void retrieveTaxByState() throws FlooringPersistenceException{

        //arrange: setup to create in memory
        Tax testTax = new Tax("CT", new BigDecimal(6.25));

        //create in memory
        taxesDao.createTax(testTax);

        //assert: testTax in memory(Map) = State pulled off TestTax.txt file
        assertEquals(testTax,taxesDao.retrieveTaxByState("CT"));
    }

    @Test
    public void retrieveAllTaxes() throws FlooringPersistenceException{

        //arrange: setup to create in memory
        Tax testTax = new Tax("FL", new BigDecimal(5.15));

        //create in memory
        taxesDao.createTax(testTax);

        //act and assert: loadFile() + testTax in memory (MAP) = loadFile() + what is now on Map
        assertEquals(1, taxesDao.retrieveAllTaxes().size());
    }

    @Test
    public void createTax() throws FlooringPersistenceException{

        //arrange: setup to create in memory
        Tax testTax = new Tax("FL", new BigDecimal(5.15));

        //create in memory
        taxesDao.createTax(testTax);

        //assert:
        assertEquals(1,taxesDao.retrieveAllTaxes().size());
    }

    //no need to test now
    @Test
    public void removeTax() throws FlooringPersistenceException{

        //arrange: setup to create in memory
        Tax testTax = new Tax("FL", new BigDecimal(5.15));

        //create in memory
        taxesDao.createTax(testTax);

        //remove tax - come back to

        taxesDao.removeTax(testTax);

        assertEquals(0,taxesDao.retrieveAllTaxes().size());

    }
}