package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface FlooringServiceLayer {

    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException;
    List<Product> retrieveAllProducts() throws FlooringPersistenceException;
    List<Tax> retrieveAllTaxes() throws FlooringPersistenceException;
    Order processOrder(Order processOrder) throws TaxStateNotFoundException, ProductMaterialNotFoundException, FlooringPersistenceException;
    Order addOrder(Order addOrder) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;
    Order retrieveOrderByDateAndId(LocalDate orderDate, String orderId) throws FlooringPersistenceException, OrderNotFoundException;
    void removeOrder(LocalDate orderDate, String orderId) throws FlooringPersistenceException, OrderNotFoundException;
    Order editOrder(LocalDate orderDate, Order editOrder) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;
    void saveAllOrders() throws FlooringPersistenceException;
    boolean activateTrainingMode(boolean userInput);

}
