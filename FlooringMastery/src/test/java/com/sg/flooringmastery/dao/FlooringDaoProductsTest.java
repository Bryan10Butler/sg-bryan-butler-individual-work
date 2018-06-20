package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoProductsTest {

    FlooringDaoProducts productsDao;

    public FlooringDaoProductsTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        productsDao = ctx.getBean("productsDao", FlooringDaoProducts.class);
    }

    //Good known state
    @Before
    public void setUp() throws Exception {

        List<Product> productList = productsDao.retrieveAllProducts();

        for(Product tempProduct : productList) {
            productsDao.removeProduct(tempProduct);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveProductByMaterial() throws FlooringPersistenceException {

        //arrange: setup to create in memory
        Product testProduct = new Product("Wood", new BigDecimal(30), new BigDecimal(45));

        //Reading only from file
        //Create in memory (add to Map)
        productsDao.createProduct(testProduct);

        //assert: testProduct in memory (Map) = State pulled off TestTax.txt file
        assertEquals(testProduct, productsDao.retrieveProductByMaterial("Wood"));
    }

    @Test
    public void retrieveAllProducts() throws FlooringPersistenceException {

        //arrange: setup to create in memory
        Product testProduct = new Product("Wood", new BigDecimal(30), new BigDecimal(45));
        //arrange: setup to create in memory
        Product testProductTwo = new Product("Metal", new BigDecimal(30), new BigDecimal(45));

        //create testProduct
        productsDao.createProduct(testProduct);
        productsDao.createProduct(testProductTwo);

        //assert: Expected size 3, retrieve size from file 3
        assertEquals(2, productsDao.retrieveAllProducts().size());
    }

    @Test
    public void createProduct() throws FlooringPersistenceException {

        //arrange: setup to create in memory
        Product testProduct = new Product("Wood", new BigDecimal(30), new BigDecimal(45));

        productsDao.createProduct(testProduct);

        assertEquals(1,productsDao.retrieveAllProducts().size());
    }

    @Test
    public void removeProduct() throws FlooringPersistenceException {

        //arrange: setup to create in memory
        Product testProduct = new Product("Wood", new BigDecimal(30), new BigDecimal(45));

        productsDao.createProduct(testProduct);

        productsDao.removeProduct(testProduct);

        assertEquals(0,productsDao.retrieveAllProducts().size());
    }
}