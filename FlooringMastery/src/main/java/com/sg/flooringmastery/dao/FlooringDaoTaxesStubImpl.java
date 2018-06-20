package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoTaxesStubImpl implements FlooringDaoTaxes {

    Tax taxObject;
    List<Tax> taxList = new ArrayList<>();

    FlooringDaoTaxesStubImpl() {
        taxObject = new Tax("CT", new BigDecimal(10));

        //add taxObject to taxList
        taxList.add(taxObject);
    }

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException {

        if (state.equals(taxObject.getState())) {
            return taxObject;
        } else {
            return null;
        }//end of else
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        return taxList;
    }

    @Override
    public void createTax(Tax taxObject) throws FlooringPersistenceException {

    }

    @Override
    public void updateTax(Tax taxObject) throws FlooringPersistenceException {

    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException {

    }
}
