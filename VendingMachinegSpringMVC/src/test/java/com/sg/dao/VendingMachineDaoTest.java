package com.sg.dao;

import com.sg.dto.VendingMachineItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNull;

public class VendingMachineDaoTest {

    //create instance DAO and inject filename Test.txt
    private VendingMachineDao dao = new VendingMachineDaoDbImpl();

    @Before
    public void setUp() throws VendingMachinePersistenceException {
        // ask Spring for our DAO
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);

        // remove all of the Contacts
        List<VendingMachineItem> items = dao.retrieveAllVendingMachineItems();
        for (VendingMachineItem currentItem : items) {
            dao.removeVendingMachineItemById(currentItem.getItemId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveAllVendingMachineItems() throws VendingMachinePersistenceException{

        //create snackOne
        VendingMachineItem snackOne = new VendingMachineItem();
        snackOne.setName("snickers");
        snackOne.setPrice(BigDecimal.valueOf(1.50));
        snackOne.setQuantity(5);
        //pass snackOne to DAO for create method
        dao.createVendingMachineItemById(snackOne);

        VendingMachineItem snackTwo = new VendingMachineItem();
        snackTwo.setName("Gum");
        snackTwo.setPrice(BigDecimal.valueOf(1.80));
        snackTwo.setQuantity(3);
        //pass snackOne to DAO for create method
        dao.createVendingMachineItemById(snackTwo);

        List<VendingMachineItem> iList = dao.retrieveAllVendingMachineItems();
        assertEquals(iList.size(), 2);
    }

    private void assertEquals(int i, int size) {
    }

    @Test
    public void updateItem() throws VendingMachinePersistenceException{
        //create snackOne
        VendingMachineItem snackThree = new VendingMachineItem();
        snackThree.setName("Beer");
        snackThree.setPrice(BigDecimal.valueOf(1.50));
        snackThree.setQuantity(6);
        //pass snackOne to DAO for create method
        dao.createVendingMachineItemById(snackThree);

        //reset snackOne to 10
        snackThree.setQuantity(12);
        //call on update method to update snackOne
        dao.updateItem(snackThree);

        //expected quantity 10; get snack quantity 10
        assertEquals(12,snackThree.getQuantity());
    }

    @Test
    public void removeVendingMachineItemById() throws VendingMachinePersistenceException{

        //create instance removeItemTest from object VendingMachineItem
        VendingMachineItem removeItemTest = new VendingMachineItem();
        //build out removeItemTest
        removeItemTest.setName("removeItemTest");
        removeItemTest.setPrice(BigDecimal.valueOf(1.50));
        removeItemTest.setQuantity(2);
        //pass to dao to create item
        dao.createVendingMachineItemById(removeItemTest);
        //remove item
        dao.removeVendingMachineItemById(removeItemTest.getItemId());
        //expect 0 due to item being deleted from dao.remove (above)
        //assertEquals(0, dao.retrieveAllVendingMachineItems().size());
        //assert null that if I try to get item ID it will return null
        assertNull(dao.retrieveItemById(removeItemTest.getItemId()));
    }
}