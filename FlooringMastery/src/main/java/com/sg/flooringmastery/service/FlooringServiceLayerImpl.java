package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    private FlooringDaoOrder orderDao;
    private FlooringDaoProducts productsDao;
    private FlooringDaoTaxes taxesDao;

    public FlooringServiceLayerImpl(FlooringDaoOrder orderDao, FlooringDaoProducts productsDao, FlooringDaoTaxes taxesDao) {
        this.orderDao = orderDao;
        this.productsDao = productsDao;
        this.taxesDao = taxesDao;
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException {

        //Validate order date exists
        validateOrdersExistForDate(orderDate);

        //Retrieve all orders by date store in allOrderByDate
        List allOrderByDate = orderDao.retrieveAllOrdersByDate(orderDate);

        return allOrderByDate;
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {

        //Retrieve and return all product information
        return productsDao.retrieveAllProducts();
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {

        //Retrieve and return all tax information
        List allTaxes = taxesDao.retrieveAllTaxes();

        return allTaxes;
    }

    @Override
    public Order processOrder(Order processOrder) throws TaxStateNotFoundException, ProductMaterialNotFoundException, FlooringPersistenceException {

        //Get state from user input
        String orderState = processOrder.getTaxObject().getState();

        //Check that tax state exists
        if (retrieveTaxObject(orderState) == null) {
            //If not throw exception
            throw new TaxStateNotFoundException("====State not found====");
        } else {
            //Set state that was input by user
            processOrder.setTaxObject(retrieveTaxObject(orderState));
        }

        //Check that product material exists
        String orderMaterial = processOrder.getProductObject().getProductType();

        //Check that material exists
        if (retrieveProductObject(orderMaterial) == null) {
            //If not throw exception
            throw new ProductMaterialNotFoundException("====Material not found====");
        } else {
            //Set material passed by user
            processOrder.setProductObject(retrieveProductObject(orderMaterial));
        }
        //Return processed order
        return processOrder;
    }

    @Override
    public Order addOrder(Order addOrder) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException {

        //Call process order
        addOrder = processOrder(addOrder);

        //TotalMaterialCost store in add order
        addOrder = calculateAndSetTotalMaterialCost(addOrder);

        //TotalLaborCost store in add order
        addOrder = calculateAndSetTotalLaborCost(addOrder);

        //TotalTaxCost store in add order
        addOrder = calculateAndSetTotalTaxCost(addOrder);

        //TotalCost store in add order
        addOrder = calculateAndSetTotalCost(addOrder);

        //Pass down to order Dao to create order and return and store as createdOrder
        Order createdOrder = orderDao.createOrder(addOrder.getOrderDate(), addOrder);

        return createdOrder;
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderId) throws FlooringPersistenceException, OrderNotFoundException {

        //validateDateExists(orderDate);
        validateOrdersExistForDate(orderDate);

        //Pass order date and id to Dao and store as retrieved order
        Order retrievedOrder = orderDao.retrieveOrderByDateAndId(orderDate, orderId);

        if (retrievedOrder == null) {
            throw new OrderNotFoundException("====Order Not Found====");
        }

        return retrievedOrder;
    }

    @Override
    public void removeOrder(LocalDate orderDate, String orderId) throws FlooringPersistenceException, OrderNotFoundException {

        //validateDateExists(orderDate);
        validateOrdersExistForDate(orderDate);

        //Call to remove order to remove order
        orderDao.removeOrder(orderDate, orderId);

    }

    @Override
    //User is passing down an edited order
    public Order editOrder(LocalDate orderDate, Order editOrder) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException {

        //Call process order
        editOrder = processOrder(editOrder);

        //TotalMaterialCost store in add order
        editOrder = calculateAndSetTotalMaterialCost(editOrder);

        //TotalLaborCost store in add order
        editOrder = calculateAndSetTotalLaborCost(editOrder);

        //TotalTaxCost store in add order
        editOrder = calculateAndSetTotalTaxCost(editOrder);

        //TotalCost store in add order
        editOrder = calculateAndSetTotalCost(editOrder);

        //If original date (orderDate) != update date
        if (orderDate != editOrder.getOrderDate()) {
            //Create order with new order Date (editOrder.getOrderDate)
            orderDao.createOrder(editOrder.getOrderDate(),editOrder);
            //Remove orderDate (original date)
            orderDao.removeOrder(orderDate, editOrder.getOrderNumber());
        } else {
            //else (dates are equal), Update
            orderDao.updateOrder(editOrder.getOrderDate(), editOrder);
        }
        return editOrder;
    }

    @Override
    public void saveAllOrders() throws FlooringPersistenceException {

        orderDao.saveOrders();
    }

    @Override
    public boolean activateTrainingMode(boolean userInput) {

        //Application context setup
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        if (userInput == true) {
            orderDao = ctx.getBean("orderTrainingDao", FlooringDaoOrderTrainingFileImpl.class);
        } else {
            orderDao = ctx.getBean("orderProdDao", FlooringDaoOrderProdFileImpl.class);
        }
        return userInput;
    }

    private void validateOrdersExistForDate(LocalDate orderDate) throws OrderNotFoundException, FlooringPersistenceException {

        //If orders within order date is size zero return order not found
        if (orderDao.retrieveAllOrdersByDate(orderDate).size() == 0) {
            throw new OrderNotFoundException("====Order not found====");
        }
    }

    private Tax retrieveTaxObject(String taxObject) throws FlooringPersistenceException {
        return taxesDao.retrieveTaxByState(taxObject);
    }

    private Product retrieveProductObject(String productObject) throws FlooringPersistenceException {
        return productsDao.retrieveProductByMaterial(productObject);
    }

    private Order calculateAndSetTotalMaterialCost(Order orderObject) {
        //total material cost = materialCostPerSqFoot * Area
        BigDecimal area = orderObject.getArea();

        BigDecimal materialPerSqFoot = orderObject.getProductObject().getMaterialCostPerSquareFoot();

        BigDecimal totalMaterialCost = area.multiply(materialPerSqFoot);

        orderObject.setTotalMaterialCost(totalMaterialCost);

        return orderObject;
    }

    private Order calculateAndSetTotalLaborCost(Order orderObject) {
        //total labor cost = laborCostPerSqFoot * Area
        BigDecimal area = orderObject.getArea();

        BigDecimal laborPerSqFoot = orderObject.getProductObject().getLabCostPerSquareFoot();

        BigDecimal totalLaborCost = area.multiply(laborPerSqFoot);

        orderObject.setTotalLaborCost(totalLaborCost);

        return orderObject;
    }

    private Order calculateAndSetTotalTaxCost(Order orderObject) {
        //total tax cost = (material cost + labor cost) * tax rate for state
        BigDecimal materialCostPerSqFoot = orderObject.getProductObject().getMaterialCostPerSquareFoot();

        BigDecimal laborCostPerSqFoot = orderObject.getProductObject().getLabCostPerSquareFoot();

        //.movePointLeft moves the decimal point
        BigDecimal taxRate = (orderObject.getTaxObject().getTaxRate()).movePointLeft(2);

        BigDecimal sumOfMaterialCost = materialCostPerSqFoot.add(laborCostPerSqFoot);

        BigDecimal totalTaxCost = sumOfMaterialCost.multiply(taxRate);

        orderObject.setTotalTax(totalTaxCost);

        return orderObject;
    }

    private Order calculateAndSetTotalCost(Order orderObject) {
        //total cost = total tax + total labor + total material
        BigDecimal totalTax = orderObject.getTotalTax();

        BigDecimal totalLabor = orderObject.getTotalLaborCost();

        BigDecimal totalMaterial = orderObject.getTotalMaterialCost();

        BigDecimal totalCost = totalTax.add(totalLabor).add(totalMaterial);

        orderObject.setTotalCost(totalCost);

        return orderObject;
    }

    /*
    private void validateDateExists(LocalDate orderDate) throws OrderNotFoundException {
        try{
            orderDao.retrieveAllOrdersByDate(orderDate);
        }catch (FlooringPersistenceException e) {
            throw new OrderNotFoundException("====Date does not exist====");
        }
    }
    */
}
