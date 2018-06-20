package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlooringDaoOrderTrainingFileImpl implements FlooringDaoOrder {

    //Map orderByDateMap with outer key LocalDate and inner HashMap key Integer
    private Map<LocalDate, HashMap<String, Order>> ordersByDateMap = new HashMap<>();
    private final String DELIMITER = ",";

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {

        //If map does not contain orders within orderDate passed
        if (!ordersByDateMap.containsKey(orderDate)) {
            //Load orders by orderDate
            loadOrders(orderDate);
        }

        //If map does not contain orders within orderDate throw exception
        if (ordersByDateMap.get(orderDate) == null) {
            throw new FlooringPersistenceException("No orders for given date.");
        }

        //Access orders in Map by orderDate key
        //Get all values associated with key orderDate (values held within inner HashMap)
        List orderList = new ArrayList<>(ordersByDateMap.get(orderDate).values());

        return orderList;
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String id) throws FlooringPersistenceException {
        //If map does not contain orders within orderDate passed
        if (!ordersByDateMap.containsKey(orderDate)) {

            //Figure this line out
            loadOrders(orderDate);
        }

        //Get orderDate and Id
        Order orderByDateAndId = ordersByDateMap.get(orderDate).get(id);

        return orderByDateAndId;
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order createOrder) throws FlooringPersistenceException {

        //Generate order number and assign to generateOrderNumber
        String generatedOrderNumber = generateOrderNumber(orderDate);

        //Set generateOrderNumber by passing generatedOrderNumber into Set
        createOrder.setOrderNumber(generatedOrderNumber);

        //If orderDate does not already exit
        if (ordersByDateMap.get(orderDate) == null) {

            //Put orderDate into new HashMap container
            ordersByDateMap.put(orderDate, new HashMap<>());
            //Get orderDate (Key).
            //Put new values into the "inner" map
            ordersByDateMap.get(orderDate).put(createOrder.getOrderNumber(), createOrder);
        } else {
            //Get orderDate(Key)
            //Put new values into already existing map
            ordersByDateMap.get(orderDate).put(createOrder.getOrderNumber(), createOrder);
        }

        return createOrder;

    }

    @Override
    public void updateOrder(LocalDate orderDate, Order updateOrder) throws FlooringPersistenceException {

        //Update to map
        ordersByDateMap.get(orderDate).replace(updateOrder.getOrderNumber(), updateOrder);

    }

    @Override
    public void removeOrder(LocalDate orderDate, String removeOrder) throws FlooringPersistenceException {

        //Remove from map
        ordersByDateMap.get(orderDate).remove(removeOrder);

    }

    @Override
    public void saveOrders() throws FlooringPersistenceException {

        //Call write orders
        //writeOrders();
    }

    private String generateOrderNumber(LocalDate orderDate) throws FlooringPersistenceException {

        //String highestUsedOrderNumber;
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        //Retrieve all orders by date
        List<Order> ordersByDate = retrieveAllOrdersByDate(orderDate);

        //for all orders by date store in tempOrders
        if (ordersByDate != null) {

            //For all tempCurrentOrder in orders by date
            for (Order tempCurrentOrder : ordersByDate) {

                //If tempCurrentOrder order's number is greater than 0
                //if (tempCurrentOrder.getOrderNumber() > highestUsedOrderNumber) {

                //Assign tempCurrentOrder order's number to highestUsed
                randomUUIDString = tempCurrentOrder.getOrderNumber();
            }
        }
        //}
        //increment by 1
        return randomUUIDString;
    }

    private void loadOrders(LocalDate orderDate) throws FlooringPersistenceException {

        Scanner scanner = null;
        //FileName with specific format
        String fileName = ("Orders_" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt");

        try {
            //Create new scanner for reading fileName: "orders_06012013.txt" created above
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

        } catch (FileNotFoundException e) {
            //If file not found put into HashMap container
            ordersByDateMap.put(orderDate, new HashMap<>());
            //Lets our service layer method know that something is missing
            //throw new FlooringPersistenceException("No orders exist for given date");
        }

        //Current line holds the most recent line read from the file
        String currentLine;
        //Current token holds each of the parts of the current line after split by delimiter
        String[] currentTokens;
        //Put orderDate into map along with empty map container
        ordersByDateMap.put(orderDate, new HashMap<>());

        //If file has data do the following
        if (scanner != null) {
            while (scanner.hasNextLine()) {//while there are lines left to read in the file
                //Read the current line
                currentLine = scanner.nextLine();
                //Split current line by delimiter ","
                currentTokens = currentLine.split(DELIMITER);//split up line into chunks
                //Create instance orderObject of object Order
                Order orderObject = new Order();
                //Store each delimited piece of current line into separate indexes of String []
                orderObject.setOrderNumber((currentTokens[0]));
                orderObject.setCustomerName(currentTokens[1]);
                orderObject.setTaxObject(new Tax(currentTokens[2], new BigDecimal(currentTokens[3])));
                orderObject.setProductObject(new Product(currentTokens[4], new BigDecimal(currentTokens[6]), new BigDecimal(currentTokens[7])));
                orderObject.setArea(new BigDecimal(currentTokens[5]));
                orderObject.setTotalMaterialCost(new BigDecimal(currentTokens[8]));
                orderObject.setTotalLaborCost(new BigDecimal(currentTokens[9]));
                orderObject.setTotalTax(new BigDecimal(currentTokens[10]));
                orderObject.setTotalCost(new BigDecimal(currentTokens[11]));
                orderObject.setOrderDate(orderDate);
                //Get orderDate (Key) to access our inner map
                //Put our new orderObject into our inner map
                ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
            }//end of while
            scanner.close();
        }//end of if
    }

    /*
    //Write orders to file
    //because we are using "this." we are referencing the map up top
    private void writeOrders() throws FlooringPersistenceException {

        PrintWriter out;
        String fileName;

        //Create a new set as orderDateSet
        //Store dates into orderDateSet
        Set<LocalDate> orderDateSet = this.ordersByDateMap.keySet();

        //For every date in orderDateSet put into orderDate
        for (LocalDate orderDate : orderDateSet) {

            //FileName with specific format
            fileName = ("Orders_" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt");

            try {

                //Assign fileName: "orders_06012013.txt" created above to out
                out = new PrintWriter(new FileWriter(fileName));

            } catch (IOException e) {

                throw new FlooringPersistenceException("Could not Write to file");
            }//end of catch


            //Retrieve all orders by date for associated order date
            List<Order> orderList = this.retrieveAllOrdersByDate(orderDate);

            //For all current temp orders in orderList
            for (Order currentTempOrder : orderList) {

                //Get order information associated with orderDate and print order date file
                out.println(currentTempOrder.getOrderNumber() + DELIMITER +
                        currentTempOrder.getCustomerName() + DELIMITER +
                        currentTempOrder.getTaxObject().getState() + DELIMITER +
                        currentTempOrder.getTaxObject().getTaxRate() + DELIMITER +
                        currentTempOrder.getProductObject().getProductType() + DELIMITER +
                        currentTempOrder.getArea() + DELIMITER +
                        currentTempOrder.getProductObject().getMaterialCostPerSquareFoot() + DELIMITER +
                        currentTempOrder.getProductObject().getLabCostPerSquareFoot() + DELIMITER +
                        currentTempOrder.getTotalMaterialCost() + DELIMITER +
                        currentTempOrder.getTotalLaborCost() + DELIMITER +
                        currentTempOrder.getTotalTax() + DELIMITER +
                        currentTempOrder.getTotalCost());

                out.flush();
            }
            out.close();
        }
    }//end of write
    */
}

