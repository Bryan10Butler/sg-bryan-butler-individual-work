package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class FlooringServiceLayerTest {

    LocalDate orderDate = LocalDate.parse("02212018", DateTimeFormatter.ofPattern("MMddyyyy"));
    FlooringServiceLayer service;

    public FlooringServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayerImpl", FlooringServiceLayer.class);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveAllOrdersByDate() throws FlooringPersistenceException, OrderNotFoundException {

        //Assert: Expect 1 order, retrieve 1 order from Service > Stub
        assertEquals(1, service.retrieveAllOrdersByDate(orderDate).size());
    }

    @Test
    public void retrieveAllProducts() throws FlooringPersistenceException {

        //Assert: Expect 1, retrieve all (1 order) orders from Service > Stub
        assertEquals(1, service.retrieveAllProducts().size());
    }

    @Test
    public void retrieveAllTaxes() throws FlooringPersistenceException {

        //Assert: Expect 1, retrieve all (1 State 1 Tax) from Service > Stub
        assertEquals(1, service.retrieveAllTaxes().size());

    }

    @Test
    public void addOrder() throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException, OrderNotFoundException {

        //Testing add order method works by referencing what we have in our stub
        service.addOrder(service.retrieveOrderByDateAndId(orderDate, "1"));

    }

    @Test
    public void retrieveOrderByDateAndId() throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException, OrderNotFoundException {

        //Retrieve order by date and ID should return what we have in our stub
        assertNotNull(service.retrieveOrderByDateAndId(orderDate, "1"));

        //Retrieve should return null as we do not have ID 23 in our stub
        assertNull(service.retrieveOrderByDateAndId(orderDate, "23"));

    }

    @Test
    public void removeOrder() throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException, OrderNotFoundException {

        //Act: Create order instance
        Order orderOne = new Order();

        //Act: Initialize order instance
        orderOne.setOrderNumber("1");
        orderOne.setOrderDate(orderDate);
        orderOne.setCustomerName("Bryan");
        orderOne.setArea(new BigDecimal(10));
        orderOne.setTotalMaterialCost(new BigDecimal(10));
        orderOne.setTotalLaborCost(new BigDecimal(10));
        orderOne.setTotalTax(new BigDecimal(10));
        orderOne.setTotalCost(new BigDecimal(10));
        orderOne.setTaxObject(new Tax("CT", new BigDecimal(10)));
        orderOne.setProductObject(new Product("Wood", new BigDecimal(20), new BigDecimal(20)));

        //Act: Pass order into Create order method via parameter
        Order createdOrder = service.addOrder(orderOne);

        //Act: Remove created order
        service.removeOrder(orderDate,createdOrder.getOrderNumber());

        //Assert: Expected 1 due to stub data, retrieve all order by date
        assertEquals(1,service.retrieveAllOrdersByDate(orderDate).size());
    }

    @Test
    public void editOrder() throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException, OrderNotFoundException {

        //Test pass through
        service.editOrder(orderDate, service.retrieveOrderByDateAndId(orderDate, "1"));
    }

    @Test
    public void testProcessOrder() throws Exception{

        //Test pass through
        service.processOrder(service.retrieveOrderByDateAndId(orderDate, "1"));
    }

    @Test
    public void saveAllOrders() throws Exception {

        //Test pass through
        service.saveAllOrders();
    }

    @Test
    public void activateTrainingMode() {
    }

    @Test
    public void calculateAndSetTotalMaterialCost() throws Exception {

        //Retrieve order by date and id and store in orderOne
        Order orderOne = service.retrieveOrderByDateAndId(orderDate, "1");

        //Calculate against data returned in orderOne
        assertEquals(new BigDecimal("600"), service.addOrder(orderOne).getTotalMaterialCost());
    }

    @Test
    public void calculateAndSetTotalLaborCost() throws Exception{

        //Retrieve order by date and id and store in orderOne
        Order orderOne = service.retrieveOrderByDateAndId(orderDate, "1");

        //Calculate against data returned in orderOne
        assertEquals(new BigDecimal("600"), service.addOrder(orderOne).getTotalLaborCost());
    }

    @Test
    public void calculateAndSetTotalTaxCost() throws Exception{

        //Retrieve order by date and id and store in orderOne
        Order orderOne = service.retrieveOrderByDateAndId(orderDate, "1");

        //Calculate against data returned in orderOne
        assertEquals(new BigDecimal("4.00"), service.addOrder(orderOne).getTotalTax());
    }

    @Test
    public void calculateAndSetTotalCost() throws Exception {

        //Retrieve order by date and id and store in orderOne
        Order orderOne = service.retrieveOrderByDateAndId(orderDate, "1");

        //Calculate against data returned in orderOne
        assertEquals(new BigDecimal("1204.00"), service.addOrder(orderOne).getTotalCost());
    }

    @Test
    public void testOrderNotFoundException() throws Exception {

        //Use bad date
        LocalDate badDate = LocalDate.now();

        try {
            //Compare bad date against stub date
            //Fail if exception is not caught
            service.retrieveAllOrdersByDate(badDate);
                fail("Expected Order not found Exception never thrown");
        }catch (OrderNotFoundException e)  {
            //if Order Not Found Exception is caught, successful test
        }
    }

    @Test
    public void testProductMaterialNotFoundException() throws Exception {

        try {
            //Pass "bad" material
            //Fail if exception is not caught
            Order orderOne = service.retrieveOrderByDateAndId(orderDate, "1");
            orderOne.getProductObject().setProductType("Grass");
            service.addOrder(orderOne);
            fail("Expected Product not found exception");
        }catch (ProductMaterialNotFoundException e) {
            //if Product Not Found Exception is caught, successful test
        }
    }

    @Test
    public void testTaxStateNotFoundException() throws Exception {

        try {
            //Pass "bad" state
            //Fail if exception is not caught
            Order orderOne = service.retrieveOrderByDateAndId(orderDate, "1");
            orderOne.getTaxObject().setState("PA");
            service.addOrder(orderOne);
            fail("Expected Tax State not found exception");
        }catch (TaxStateNotFoundException e) {
            //if Product Not Found Exception is caught, successful test
        }

    }

    @Test
    public void testHappyPathNoException() throws Exception {

        //Retrieve stub data store in orderOne
        Order orderOne = service.retrieveOrderByDateAndId(orderDate, "1");

        //Add orderOne where all data is correct
        service.addOrder(orderOne);
    }
}