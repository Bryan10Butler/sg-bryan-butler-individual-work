package com.sg.dao;

import com.sg.dto.VendingMachineItem;

import java.util.List;

public interface VendingMachineDao {


    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;

    void updateItem(VendingMachineItem itemToBeUpdated) throws VendingMachinePersistenceException;

    VendingMachineItem retrieveItemById(long itemId) throws VendingMachinePersistenceException;

    void removeVendingMachineItemById(long itemId) throws VendingMachinePersistenceException;


    //Class Roster had different setup
    VendingMachineItem createVendingMachineItemById(VendingMachineItem createItem)
            throws VendingMachinePersistenceException;

}
