package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.service.FlooringServiceLayer;
import com.sg.flooringmastery.ui.FlooringView;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FlooringDaoProductsFileImpl implements FlooringDaoProducts {

    private Map<String, Product> productMap = new HashMap<>();
    private static String productsFile;
    private static final String DELIMITER = ",";

    public FlooringDaoProductsFileImpl(String FILENAME) {
        this.productsFile = FILENAME;
    }

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException {

        loadProducts();

        Product productObject = productMap.get(material);

        return productObject;
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {

        loadProducts();

        List productList = new ArrayList<>(productMap.values());


        return productList;
    }

    @Override
    public void createProduct(Product productObject) throws FlooringPersistenceException {

        productMap.put(productObject.getProductType(), productObject);

        writeProducts();

    }

    @Override
    public void updateProduct(Product productObject) throws FlooringPersistenceException {

        productMap.put(productObject.getProductType(), productObject);

        writeProducts();

    }

    @Override
    public void removeProduct(Product productObject) throws FlooringPersistenceException {

        productMap.remove(productObject.getProductType(), productObject);

        writeProducts();

    }

    //load products into Map
    private void loadProducts() throws FlooringPersistenceException {

        Scanner scanner;

        try {
            //Create new scanner for reading fileName: "orders_06012013.txt" created above
            scanner = new Scanner(new BufferedReader(new FileReader(productsFile)));

        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not load Product data into memory", e);
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

            //Create instance productObject of object Order
            //Store each delimited piece of current line into separate indexes of String []
            Product productObject = new Product(currentTokens[0], new BigDecimal(currentTokens[1]),
                    new BigDecimal(currentTokens[2]));

            //Put our new productObject(key) into our map with productObject
            productMap.put(productObject.getProductType(), productObject);

        }//end of while
        scanner.close();
    }

    private void writeProducts() throws FlooringPersistenceException {

        PrintWriter out;

        try {
            //Assign fileName: "orders_06012013.txt" to out
            out = new PrintWriter(new FileWriter(productsFile));
        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not write to file");
        }

        //Retrieve all orders by date for associated order date
        List<Product> productList = this.retrieveAllProducts();
        //For all tempProduct in product List
        for (Product tempProduct : productList) {

            //Get order information associated with orderDate and print order date file
            out.println(tempProduct.getProductType() + DELIMITER + tempProduct.getMaterialCostPerSquareFoot() +
                    DELIMITER + tempProduct.getLabCostPerSquareFoot());

            out.flush();
        }
        out.close();
    }//end of write products
}
