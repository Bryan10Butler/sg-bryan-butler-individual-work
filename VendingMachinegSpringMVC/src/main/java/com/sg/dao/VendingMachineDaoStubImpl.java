package com.sg.dao;

import com.sg.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

    VendingMachineItem snackDaoStubImpl;
    List<VendingMachineItem> itemList = new ArrayList<>();

    public VendingMachineDaoStubImpl(){

        snackDaoStubImpl = new VendingMachineItem();
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
    public void updateItem(VendingMachineItem itemToBeUpdated) throws VendingMachinePersistenceException {
    }

    @Override
    public VendingMachineItem retrieveItemById(long itemId) throws VendingMachinePersistenceException {
        if (itemId == (snackDaoStubImpl.getItemId())) {
            return snackDaoStubImpl;
        }else {
            return null;
        }
    }

    @Override
    public void removeVendingMachineItemById(long itemId) throws VendingMachinePersistenceException {

    }

//    @Override
//    public VendingMachineItem removeVendingMachineItemById(long itemId) throws VendingMachinePersistenceException {
//        if (itemId == (snackDaoStubImpl.getItemId())) {
//            return snackDaoStubImpl;
//        }else {
//            return null;
//        }
//    }

    @Override
    public VendingMachineItem createVendingMachineItemById(VendingMachineItem createItem) throws VendingMachinePersistenceException {
        if (createItem.equals(snackDaoStubImpl.getItemId())) {
            return snackDaoStubImpl;
        }else {
            return null;
        }
    }
}
