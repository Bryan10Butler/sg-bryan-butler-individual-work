package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.VendingMachineChange;
import com.sg.vendingmachine.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;

    //what to pass in
    BigDecimal addMoneyToMemory(BigDecimal moneyToBeAdded);

    VendingMachineItem purchaseItem(String vendingMachineItem) throws VendingMachinePersistenceException,
            VendingMachineItemUnavailableException, VendingMachineInsufficientFundsException;

    VendingMachineChange convertDollarsToChange();

}
