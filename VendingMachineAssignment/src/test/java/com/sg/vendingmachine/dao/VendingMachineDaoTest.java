package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingMachineItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineDaoTest {

    //create instance DAO and inject filename Test.txt
    private VendingMachineDao dao = new VendingMachineDaoFileImpl("src/test/Test.txt");

    @Before
    public void setUp() throws Exception {

        //know good state
        //go to DAO, retrieve all items from map
        //return items back and store as itemList
        List<VendingMachineItem> itemList = dao.retrieveAllVendingMachineItems();

        //for every item in itemList store in retrievedList
        //pass retrievedList to dao for removing
        for (VendingMachineItem retrievedList : itemList) {
            dao.removeVendingMachineItemById(retrievedList.getItemId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveAllVendingMachineItems() throws VendingMachinePersistenceException{

        //create snackOne
        VendingMachineItem snackOne = new VendingMachineItem("snackOne");
        snackOne.setName("snackOne");
        snackOne.setQuantity(5);
        snackOne.setPrice(BigDecimal.valueOf(1.50));
        //pass snackOne to DAO for create method
        dao.createVendingMachineItemById(snackOne);

        //create snackTwo
        VendingMachineItem snackTwo = new VendingMachineItem("snackTwo");
        snackTwo.setName("snackTwo");
        snackTwo.setQuantity(5);
        snackTwo.setPrice(BigDecimal.valueOf(1.75));
        //pass snackTwo to DAO for create method
        dao.createVendingMachineItemById(snackTwo);
        //retrieve all snacks from the vending machine
        dao.retrieveAllVendingMachineItems();
        //expected 2; should return two items from dao method
        assertEquals(2,dao.retrieveAllVendingMachineItems().size());
    }

    @Test
    public void updateItem() throws VendingMachinePersistenceException{
        //create snackOne
        VendingMachineItem snackOne = new VendingMachineItem("snackOne");
        snackOne.setName("snackOne");
        snackOne.setQuantity(5);
        snackOne.setPrice(BigDecimal.valueOf(1.50));
        //pass snackOne to DAO for create method
        dao.createVendingMachineItemById(snackOne);

        //reset snackOne to 10
        snackOne.setQuantity(10);
        //call on update method to update snackOne
        dao.updateItem(snackOne);

        //expected quantity 10; get snack quantity 10
        assertEquals(10,snackOne.getQuantity());
    }

    @Test
    public void testAddAndRetrieveItemById() throws VendingMachinePersistenceException{

        //create new object snickers
        VendingMachineItem snickers = new VendingMachineItem("Snickers");
        snickers.setName("snickers");
        snickers.setQuantity(5);
        snickers.setPrice(BigDecimal.valueOf(1.50));
        //pass the snickers object to dao method create
        //why can I store in value to left
        dao.createVendingMachineItemById(snickers);
        //retrieve item
        //why can't I pass down as object
        VendingMachineItem fromDao = dao.retrieveItemById(snickers.getItemId());
        //expected is snickers; compare to fromDao
        assertEquals(snickers, fromDao);
    }

    @Test
    public void removeVendingMachineItemById() throws VendingMachinePersistenceException{

        //create instance removeItemTest from object VendingMachineItem
        VendingMachineItem removeItemTest = new VendingMachineItem("removeItemTest");
        //build out removeItemTest
        removeItemTest.setName("removeItemTest");
        removeItemTest.setQuantity(2);
        removeItemTest.setPrice(BigDecimal.valueOf(1.50));
        //pass to dao to create item
        dao.createVendingMachineItemById(removeItemTest);
        //remove item
        dao.removeVendingMachineItemById(removeItemTest.getItemId());
        //expect 0 due to item being deleted from dao.remove (above)
        assertEquals(0, dao.retrieveAllVendingMachineItems().size());
        //assert null that if I try to get item ID it will return null
        assertNull(dao.retrieveItemById(removeItemTest.getItemId()));
    }
}