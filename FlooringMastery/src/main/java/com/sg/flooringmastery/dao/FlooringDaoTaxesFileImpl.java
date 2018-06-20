package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FlooringDaoTaxesFileImpl implements FlooringDaoTaxes {
    private static String taxesFile;
    private static final String DELIMITER = ",";

    public FlooringDaoTaxesFileImpl(String FILENAME) {
        this.taxesFile = FILENAME;
    }

    private Map<String, Tax> taxRateByStateMap = new HashMap<>();

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException{

        loadTaxes();

        Tax taxObject = taxRateByStateMap.get(state);

        return taxObject;
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException{

        loadTaxes();

        List taxList = new ArrayList<>(taxRateByStateMap.values());

        return taxList;
    }

    @Override
    public void createTax(Tax taxObject) throws FlooringPersistenceException{

        taxRateByStateMap.put(taxObject.getState(), taxObject);

        writeFlooringTaxes();

    }

    @Override
    public void updateTax(Tax taxObject) throws FlooringPersistenceException{

        taxRateByStateMap.put(taxObject.getState(), taxObject);

        writeFlooringTaxes();
    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException{

        taxRateByStateMap.remove(taxObject.getState());

        writeFlooringTaxes();
    }

    //load taxes into Map
    private void loadTaxes() throws FlooringPersistenceException {

        //loads taxes into the map

        //creating an instance of Scanner
        Scanner scanner;

        try {

            //Create new scanner for reading fileName: "orders_06012013.txt" created above
            scanner = new Scanner(new BufferedReader(new FileReader(taxesFile)));

        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not load tax data into memory", e);
        }

        //Current line holds the most recent line read from the file
        String currentLine;
        //Current token holds each of the parts of the current line after split by delimiter
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            //Read the current line
            currentLine = scanner.nextLine();
            //Split current line by delimiter ","
            currentTokens = currentLine.split(DELIMITER);

            //Create instance taxObject of object Order
            //Store each delimited piece of current line into separate indexes of String []
            Tax currentTaxObject = new Tax(currentTokens[0], new BigDecimal(currentTokens[1]));

            //Put our new taxObject(key) into our map with taxObject
            taxRateByStateMap.put(currentTaxObject.getState(), currentTaxObject);

        }//end of while
        scanner.close();
    }

    private void writeFlooringTaxes() throws FlooringPersistenceException {

        PrintWriter out;

        try {
            //Assign fileName: "orders_06012013.txt" to out
            out = new PrintWriter(new FileWriter(taxesFile));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not save item data.", e);
        }

        //Retrieve all orders by date for associated order date
        List<Tax> taxItemList = this.retrieveAllTaxes();
        //For all tempProduct in product List
        for (Tax taxCurrentTax : taxItemList) {
            //Get order information associated with orderDate and print order date file
            out.println(taxCurrentTax.getState() + DELIMITER
                    + taxCurrentTax.getTaxRate());
            out.flush();
        }
        // Clean up
        out.close();
    }
}
