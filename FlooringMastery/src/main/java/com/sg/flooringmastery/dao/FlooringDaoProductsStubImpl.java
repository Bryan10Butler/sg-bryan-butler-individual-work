package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoProductsStubImpl implements FlooringDaoProducts {

    Product productObject;
    List<Product> productList = new ArrayList<>();

    public FlooringDaoProductsStubImpl() {

        productObject = new Product("Wood", new BigDecimal(20), new BigDecimal(20));
        productList.add(productObject);
    }

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException {

        if (material.equals(productObject.getProductType())) {

            return productObject;

        } else {
            return null;
        }
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        return productList;
    }

    @Override
    public void createProduct(Product productObject) throws FlooringPersistenceException {

    }

    @Override
    public void updateProduct(Product productObject) throws FlooringPersistenceException {

    }

    @Override
    public void removeProduct(Product productObject) throws FlooringPersistenceException {

    }
}
