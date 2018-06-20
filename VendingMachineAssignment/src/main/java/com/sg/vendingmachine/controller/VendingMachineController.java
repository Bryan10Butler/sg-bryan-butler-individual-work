package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.VendingMachineChange;
import com.sg.vendingmachine.dto.VendingMachineItem;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineItemUnavailableException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {

    //Controller has two dependencies (Service and View)
    private VendingMachineServiceLayer myService;
    private VendingMachineView myView;

    //Constructor
    //This allows any class that creates an instance of the
    //controller to pass in the values for dependency injection
    public VendingMachineController(VendingMachineServiceLayer myService, VendingMachineView myView) {
        this.myService = myService;
        this.myView = myView;
    }

    //run
    public void run() {

        boolean stillHungry = true;
        int menuSelection;

        try {
            while (stillHungry) {

                //lists vending machine items
                listAllAvailableItems();

                menuSelection = retrieveMenuSelection();

                switch (menuSelection) {

                    case 1:
                        addMoney();
                        break;
                    case 2:
                        purchaseItem();
                        break;
                    case 3:
                        getChange();
                        break;
                    case 4:
                        stillHungry = false;
                        break;
                    default:
                        System.out.println("unknown command");
                }//end of switch
            }//end of while
        }catch (VendingMachinePersistenceException e) {
            myView.displayErrorMessage(e.getMessage());
        }//end of catch
    }//end of run

    //method - display menu and prompt for user selection
    private int retrieveMenuSelection() {
        return myView.displayMenuAndPromptSelection();
    }

    //method - list all available items in vending machine at start of application
    private void listAllAvailableItems() throws VendingMachinePersistenceException {

        //call to view to print "Welcome" banner
        myView.displayWelcomeBanner();
        //call to view to print "Header(ID, SNACK NAME, SNACK PRICE, QUANTITY"  banner
        myView.displayVendingMachineHeader();
        //call to Service Layer > Dao and return List to store as ItemListFromService
        List<VendingMachineItem> ItemListFromService = myService.retrieveAllVendingMachineItems();
        //call to View to print List ItemListFromService
        myView.displayVendingMachineItemInventory(ItemListFromService);
    }

    private void addMoney() throws VendingMachinePersistenceException{

        //call to view to ask how much money to add
        BigDecimal moneyToBeAdded = myView.promptForMoneyToAdd();
        //call to service to add money to memory
        BigDecimal currentBalance = myService.addMoneyToMemory(moneyToBeAdded);
        //call to view to display amount current balance
        myView.displayCurrentBalance(currentBalance);

    }

    private void purchaseItem() throws VendingMachinePersistenceException{

        //call to view to ask what item to purchase by ID
        String itemIdToPurchase = myView.promptForItemId();

        try {
            //Call to service > dao with item ID
            //Return item object to service
            //Update quantity in Service
            //Pass item to dao to update quantity within map
            VendingMachineItem itemPurchased = myService.purchaseItem(itemIdToPurchase);
            myView.displayItem(itemPurchased);
        }catch (VendingMachineItemUnavailableException e) {
            //User message defined at service
            //the below can callAi display message to view (another option)
            myView.displayErrorMessageItemUnavailable(e.getMessage());
        }catch(VendingMachineInsufficientFundsException e) {
            //User message defined at service
            myView.displayErrorMessageItemUnavailable(e.getMessage());
        }

        //call view and pass itemPurchased to show user what they purchased
    }

    private void getChange() throws VendingMachinePersistenceException{
        //call to service layer to calculate change
        VendingMachineChange moneyInChange = myService.convertDollarsToChange();
        //call to view to display change quarters dimes nickels penny
        myView.displayChange(moneyInChange);
    }
}
