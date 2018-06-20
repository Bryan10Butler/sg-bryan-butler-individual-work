package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.VendingMachineChange;
import com.sg.vendingmachine.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

public class VendingMachineServiceFileImpl implements VendingMachineServiceLayer {

    //service layer is dependent on the audit dao
    //
    private VendingMachineAuditDao auditDao;

    //Service layer is dependent on the dao
    private VendingMachineDao dao;

    //Constructor
    //This allows any class that creates an instance of the
    //ServiceFileImpl to pass in the values for dependency injection
    public VendingMachineServiceFileImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }


    //Create instance of Big Decimal and assign values to new objects
    BigDecimal remainingMoney = new BigDecimal("0");
    BigDecimal oneHundredPennies = new BigDecimal("100");
    BigDecimal valueOfQuarter = new BigDecimal("25");
    BigDecimal valueOfDime = new BigDecimal("10");
    BigDecimal valueOfNickel = new BigDecimal("5");
    BigDecimal valueOfPenny = new BigDecimal("1");


    @Override
    //call to DAO to return a array list object
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return dao.retrieveAllVendingMachineItems();

    }

    @Override
    public BigDecimal addMoneyToMemory(BigDecimal moneyToBeAdded) {
        //add "moneyToBeAdded" to the running total of remainingMoney
        remainingMoney = remainingMoney.add(moneyToBeAdded);
        return remainingMoney;
    }

    @Override
    public VendingMachineItem purchaseItem(String itemIdToPurchase) throws VendingMachinePersistenceException,
            VendingMachineItemUnavailableException, VendingMachineInsufficientFundsException {

        //call DAO and pass itemIDToPurchase
        //Assign to purchasedItem object
        VendingMachineItem purchasedItem = dao.retrieveItemById(itemIdToPurchase);

        //If purchased item is null (invalid key stroke) throw exception to controller
        //If quantity if zero throw exception to controller
        if (purchasedItem == null || purchasedItem.getQuantity() <= 0) {
            throw new VendingMachineItemUnavailableException("Sorry - Item not found");
        }

        //if remaining money is less than the price of the item
        if (remainingMoney.compareTo(purchasedItem.getPrice()) < 0) {

            throw new VendingMachineInsufficientFundsException("Please add additional funds");
        }

        //declare and initialize new int with updated quantity
        int updatedQuantity = purchasedItem.getQuantity() - 1;
        //pass updated quantity to object purchased item
        purchasedItem.setQuantity(updatedQuantity);
        //call dao and pass updated purchased item to dao for update
        dao.updateItem(purchasedItem);

        //subtract item price from remaining money
        remainingMoney = remainingMoney.subtract(purchasedItem.getPrice());

        //write to audit dao
        //auditDao.writeAuditLog(purchasedItem.getName() + " PURCHASED");

        return purchasedItem;
    }

    @Override
    public VendingMachineChange convertDollarsToChange() {

        //convert remaining money to pennies
        remainingMoney = remainingMoney.multiply(oneHundredPennies);

        //convert pennies to Quarters, Nickels, Dimes, and Pennies
        //create instance on top of IF for correct scope
        VendingMachineChange moneyInChange = new VendingMachineChange();
        if (remainingMoney.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal numberOfQuarters[] = remainingMoney.divideAndRemainder(valueOfQuarter);
            //remainingMoney is equal to remainder
            remainingMoney = numberOfQuarters[1];
            //quarterCount is equal to number of quarters
            int quarterCount = numberOfQuarters[0].intValue();
            //pass in quarter count to set number quarters
            moneyInChange.setNumQuarters(quarterCount);
        }
        if (remainingMoney.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal numberOfDimes[] = remainingMoney.divideAndRemainder(valueOfDime);
            remainingMoney = numberOfDimes[1];
            int dimeCount = numberOfDimes[0].intValue();
            moneyInChange.setNumDimes(dimeCount);
        }
        if (remainingMoney.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal numberOfNickels[] = remainingMoney.divideAndRemainder(valueOfNickel);
            remainingMoney = numberOfNickels[1];
            int nickelCount = numberOfNickels[0].intValue();
            moneyInChange.setNumNickels(nickelCount);
        }
        if (remainingMoney.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal numberOfPennies[] = remainingMoney.divideAndRemainder(valueOfPenny);
            remainingMoney = numberOfPennies[1];
            int pennyCount = numberOfPennies[0].intValue();
            moneyInChange.setNumPennies(pennyCount);
        }

        //return moneyInChange object to controller
        return moneyInChange;

    }
}

