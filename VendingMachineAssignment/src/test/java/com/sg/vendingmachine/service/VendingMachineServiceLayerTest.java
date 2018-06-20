package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.VendingMachineChange;
import com.sg.vendingmachine.dto.VendingMachineItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineServiceLayerTest {

    //class level variable of type service layer
    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {

        //why this format - research
        //VendingMachineDao dao = new VendingMachineDaoStubImpl();
        //VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();


        //service is dependent on the dao stub we just created
        //service = new VendingMachineServiceFileImpl(dao, myAuditDao);

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
                ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        assertEquals(1, service.retrieveAllVendingMachineItems().size());
    }

    @Test
    public void addMoneyToMemory() {

        //create new Big Decimal moneyToBeAdded
        BigDecimal moneyToBeAdded = new BigDecimal(5);
        //pass moneyToBeAdded to service method addMoneyToMemory
        BigDecimal moneyAdded = service.addMoneyToMemory(moneyToBeAdded);
        //convert BigDecimal to Int
        //why
        int test = moneyAdded.intValue();
        //expected; returned is 5
        assertEquals(5, test);
    }

    @Test
    public void purchaseItem() throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineItemUnavailableException {

        //create new Big Decimal moneyToBeAdded
        BigDecimal moneyToBeAdded = new BigDecimal(5);
        //pass moneyToBeAdded to service method addMoneyToMemory
        service.addMoneyToMemory(moneyToBeAdded);

        //create duplicate snack (this already exists in our vending machine)
        VendingMachineItem snackDaoStubImpl = new VendingMachineItem("snackDaoStubImpl");
        snackDaoStubImpl.setName("snackDaoStubImpl");
        snackDaoStubImpl.setQuantity(4);
        snackDaoStubImpl.setPrice(BigDecimal.valueOf(2.50));

        //purchase the item existing in the vending machine
        String itemIdToPurchase = "snackDaoStubImpl";
        VendingMachineItem itemPurchased = service.purchaseItem(itemIdToPurchase);

        //assert that the item purchased is the item created within this test
        assertEquals(itemPurchased.getItemId(), snackDaoStubImpl.getItemId());
        //assert that the item quantity decreased from 4
        assertEquals(3, itemPurchased.getQuantity());
        //the below does not work because it was never added to the vending machine
        //leaving commented out for notes
        //assertEquals(3,snackDaoStubImpl.getQuantity());

    }

    @Test
    public void convertDollarsToChange() {

        //BigDecimal countToReturn = new BigDecimal(4);

        //create new Big Decimal moneyToBeAdded
        BigDecimal moneyToBeAdded = new BigDecimal(1.00);
        //pass moneyToBeAdded to service method addMoneyToMemory
        service.addMoneyToMemory(moneyToBeAdded);

        //convert one dollar to 4 quarters
        VendingMachineChange moneyInChange = service.convertDollarsToChange();

        //expected 4 quarters; get moneyInChange quarter count
        assertEquals(4, moneyInChange.getNumQuarters());

    }

}