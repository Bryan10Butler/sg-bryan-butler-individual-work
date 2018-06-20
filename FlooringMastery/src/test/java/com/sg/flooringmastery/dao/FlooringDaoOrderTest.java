package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.aspectj.apache.bcel.util.ClassPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoOrderTest {

    FlooringDaoOrder orderProdDao;
    LocalDate orderDate = LocalDate.parse("02212018", DateTimeFormatter.ofPattern("MMddyyyy"));

    public FlooringDaoOrderTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderProdDao = ctx.getBean("orderProdDao", FlooringDaoOrder.class);
    }

    @Before
    public void setUp() throws Exception {

        List<Order> orderList = orderProdDao.retrieveAllOrdersByDate(orderDate);

        for (Order tempOrderList : orderList) {
            orderProdDao.removeOrder(orderDate, tempOrderList.getOrderNumber());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveOrdersByDate() throws FlooringPersistenceException {

        //Act: Setup new order
        Order orderOne = new Order();
        //orderOne.setOrderNumber(1);
        orderOne.setTotalCost(new BigDecimal(20));
        orderOne.setTotalTax(new BigDecimal(20));
        orderOne.setTotalLaborCost(new BigDecimal(20));
        orderOne.setTotalMaterialCost(new BigDecimal(20));
        orderOne.setArea(new BigDecimal(20));
        orderOne.setProductObject(new Product("Metal", new BigDecimal(20), new BigDecimal(20)));
        orderOne.setTaxObject(new Tax("CT", new BigDecimal(20)));
        orderOne.setCustomerName("Bryan");

        //Act: Create new order
        orderProdDao.createOrder(orderDate, orderOne);

        //Act: Setup second new order
        Order orderTwo = new Order();
        //orderTwo.setOrderNumber(3);
        orderTwo.setTotalCost(new BigDecimal(30));
        orderTwo.setTotalTax(new BigDecimal(30));
        orderTwo.setTotalLaborCost(new BigDecimal(30));
        orderTwo.setTotalMaterialCost(new BigDecimal(30));
        orderTwo.setArea(new BigDecimal(30));
        orderTwo.setProductObject(new Product("Wood", new BigDecimal(30), new BigDecimal(30)));
        orderTwo.setTaxObject(new Tax("MA", new BigDecimal(20)));
        orderTwo.setCustomerName("Dan");

        //Act: Create second new order
        orderProdDao.createOrder(orderDate, orderTwo);

        //Assert: Expected 2, Two new objects
        assertEquals(2, orderProdDao.retrieveAllOrdersByDate(orderDate).size());
    }

    @Test
    public void testRetrieveOrderByDateAndId() throws FlooringPersistenceException {

        //Act: Setup new order
        Order orderOne = new Order();
        //orderOne.setOrderNumber(2);
        orderOne.setTotalCost(new BigDecimal(30));
        orderOne.setTotalTax(new BigDecimal(30));
        orderOne.setTotalLaborCost(new BigDecimal(30));
        orderOne.setTotalMaterialCost(new BigDecimal(30));
        orderOne.setArea(new BigDecimal(30));
        orderOne.setProductObject(new Product("Wood", new BigDecimal(30), new BigDecimal(30)));
        orderOne.setTaxObject(new Tax("MA", new BigDecimal(20)));
        orderOne.setCustomerName("Dan");
        orderOne.setOrderDate(orderDate);

        //Act: Create new order
        orderProdDao.createOrder(orderDate, orderOne);

        //Assert: Expected One, retrieve order by Date and ID
        assertEquals(orderOne,orderProdDao.retrieveOrderByDateAndId(orderDate,orderOne.getOrderNumber()));

    }

    @Test
    public void updateOrder() throws FlooringPersistenceException {

        //Act: Setup new order
        Order orderOne = new Order();
        //orderOne.setOrderNumber(4);
        orderOne.setTotalCost(new BigDecimal(30));
        orderOne.setTotalTax(new BigDecimal(30));
        orderOne.setTotalLaborCost(new BigDecimal(30));
        orderOne.setTotalMaterialCost(new BigDecimal(30));
        orderOne.setArea(new BigDecimal(30));
        orderOne.setProductObject(new Product("Wood", new BigDecimal(30), new BigDecimal(30)));
        orderOne.setTaxObject(new Tax("MA", new BigDecimal(20)));
        orderOne.setCustomerName("Dan");

        //Act: Create Order
        orderProdDao.createOrder(orderDate, orderOne);

        orderOne.setCustomerName("John");

        //Act: Update Order within DAO
        orderProdDao.updateOrder(orderDate, orderOne);

        //Assert: John, Returns order with name update
        assertEquals("John", orderOne.getCustomerName());
    }

    @Test
    public void removeOrder() throws FlooringPersistenceException {

        //Act: Setup new order
        Order orderOne = new Order();

        //orderOne.setOrderNumber(4);
        orderOne.setTotalCost(new BigDecimal(30));
        orderOne.setTotalTax(new BigDecimal(30));
        orderOne.setTotalLaborCost(new BigDecimal(30));
        orderOne.setTotalMaterialCost(new BigDecimal(30));
        orderOne.setArea(new BigDecimal(30));
        orderOne.setProductObject(new Product("Wood", new BigDecimal(30), new BigDecimal(30)));
        orderOne.setTaxObject(new Tax("MA", new BigDecimal(20)));
        orderOne.setCustomerName("Dan");

        //Act: Create Order
        orderProdDao.createOrder(orderDate, orderOne);

        //Act: Remove Order
        orderProdDao.removeOrder(orderDate, orderOne.getOrderNumber());

        //Assert 0, 0
        assertEquals(0,orderProdDao.retrieveAllOrdersByDate(orderDate).size());
    }
}