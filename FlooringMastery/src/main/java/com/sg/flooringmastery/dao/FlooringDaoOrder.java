package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;

import java.time.LocalDate;
import java.util.List;

public interface FlooringDaoOrder {

    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException;

    Order retrieveOrderByDateAndId(LocalDate orderDate, String id) throws FlooringPersistenceException;

    Order createOrder(LocalDate orderDate, Order createOrder) throws FlooringPersistenceException;

    void updateOrder(LocalDate orderDate, Order updateOrder) throws FlooringPersistenceException;

    void removeOrder(LocalDate orderDate, String removeOrder) throws FlooringPersistenceException;

    void saveOrders() throws FlooringPersistenceException;

}
