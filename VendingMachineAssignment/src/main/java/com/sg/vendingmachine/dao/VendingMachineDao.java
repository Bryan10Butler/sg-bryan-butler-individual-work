package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingMachineItem;

import java.util.List;

public interface VendingMachineDao {


    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;

    VendingMachineItem updateItem(VendingMachineItem itemToBeUpdated) throws VendingMachinePersistenceException;

    VendingMachineItem retrieveItemById(String itemId) throws VendingMachinePersistenceException;

    VendingMachineItem removeVendingMachineItemById(String itemId) throws VendingMachinePersistenceException;


    //Class Roster had different setup
    VendingMachineItem createVendingMachineItemById(VendingMachineItem createItem)
            throws VendingMachinePersistenceException;

}
