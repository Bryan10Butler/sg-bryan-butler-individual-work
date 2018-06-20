package com.sg.service;

import com.sg.dao.VendingMachinePersistenceException;
import com.sg.dto.VendingMachineChange;
import com.sg.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;

    //what to pass in
    BigDecimal addMoneyToMemory(BigDecimal moneyToBeAdded);

    VendingMachineItem purchaseItem(long vendingMachineItem) throws VendingMachinePersistenceException,
            VendingMachineItemUnavailableException, VendingMachineInsufficientFundsException;

    VendingMachineChange convertDollarsToChange();

    BigDecimal returnBalance();

    VendingMachineChange returnChange();

    BigDecimal addMoneyCoins(String amount);

}
