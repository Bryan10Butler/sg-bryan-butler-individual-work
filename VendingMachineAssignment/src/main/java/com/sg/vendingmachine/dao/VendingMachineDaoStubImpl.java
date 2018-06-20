package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

    VendingMachineItem snackDaoStubImpl;
    List<VendingMachineItem> itemList = new ArrayList<>();

    public VendingMachineDaoStubImpl(){

        snackDaoStubImpl = new VendingMachineItem("snackDaoStubImpl");
        snackDaoStubImpl.setName("snackDaoStubImpl");
        snackDaoStubImpl.setQuantity(4);
        snackDaoStubImpl.setPrice(BigDecimal.valueOf(2.50));

        itemList.add(snackDaoStubImpl);

    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public VendingMachineItem updateItem(VendingMachineItem itemToBeUpdated) throws VendingMachinePersistenceException {
        return itemToBeUpdated;
    }

    @Override
    public VendingMachineItem retrieveItemById(String itemId) throws VendingMachinePersistenceException {
        if (itemId.equals(snackDaoStubImpl.getItemId())) {
            return snackDaoStubImpl;
        }else {
            return null;
        }
    }

    @Override
    public VendingMachineItem removeVendingMachineItemById(String itemId) throws VendingMachinePersistenceException {
        if (itemId.equals(snackDaoStubImpl.getItemId())) {
            return snackDaoStubImpl;
        }else {
            return null;
        }
    }

    @Override
    public VendingMachineItem createVendingMachineItemById(VendingMachineItem createItem) throws VendingMachinePersistenceException {
        if (createItem.equals(snackDaoStubImpl.getItemId())) {
            return snackDaoStubImpl;
        }else {
            return null;
        }
    }
}
