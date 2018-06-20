package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoOrderStubImpl implements FlooringDaoOrder{

    Order orderObject;
    List<Order> orderList = new ArrayList<>();
    LocalDate orderDate = LocalDate.parse("02212018", DateTimeFormatter.ofPattern("MMddyyyy"));

    public FlooringDaoOrderStubImpl() {

        //orderObject
        orderObject = new Order();
        orderObject.setOrderNumber("1");
        //orderObject.setTotalCost(new BigDecimal(30));
        //orderObject.setTotalTax(new BigDecimal(30));
        //orderObject.setTotalLaborCost(new BigDecimal(30));
        //orderObject.setTotalMaterialCost(new BigDecimal(30));
        orderObject.setArea(new BigDecimal(30));
        orderObject.setProductObject(new Product("Wood", new BigDecimal(20), new BigDecimal(20)));
        //10 is really .10
        orderObject.setTaxObject(new Tax("CT", new BigDecimal(10)));
        orderObject.setCustomerName("Dan");
        orderObject.setOrderDate(orderDate);

        //list
        orderList.add(orderObject);
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {

        if (orderDate.equals(orderObject.getOrderDate())) {
            return orderList;
        }
        //Test failing due to null
        return new ArrayList<>();
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String id) throws FlooringPersistenceException {

        //.equals on String == On numbers
        if (orderDate.equals(orderObject.getOrderDate()) && id == orderObject.getOrderNumber()) {
            return orderObject;
        }
        return null;
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order createOrder) throws FlooringPersistenceException {


        return orderObject;

    }

    @Override
    public void updateOrder(LocalDate orderDate, Order updateOrder) throws FlooringPersistenceException {

    }

    @Override
    public void removeOrder(LocalDate orderDate, String removeOrder) throws FlooringPersistenceException {



    }

    @Override
    public void saveOrders() {

    }
}
