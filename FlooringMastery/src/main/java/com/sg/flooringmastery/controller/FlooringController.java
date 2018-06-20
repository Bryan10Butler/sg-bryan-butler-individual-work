package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringServiceLayer;
import com.sg.flooringmastery.service.OrderNotFoundException;
import com.sg.flooringmastery.service.ProductMaterialNotFoundException;
import com.sg.flooringmastery.service.TaxStateNotFoundException;
import com.sg.flooringmastery.ui.FlooringView;

import java.time.LocalDate;
import java.util.List;

public class FlooringController {

    //Properties
    private FlooringView view;
    private FlooringServiceLayer service;

    //Constructor
    public FlooringController(FlooringView view, FlooringServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    //Run method
    public void run() {
        boolean ordersNeeded = true;
        int menuSelection;
        try {
            //orderNeeded remains true until case 7 is selected
            while (ordersNeeded) {
                //Assign user's selected menu option to variable menuSelection
                menuSelection = printMenuAndRetrieveSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrdersOrdersByDate();
                        break;
                    case 2:
                        addNewOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveAllOrders();
                        break;
                    case 6:
                        trainingMode();
                        break;
                    case 7:
                        ordersNeeded = false;
                        break;
                    default:
                        unknownCommand();
                }//end of switch
            }//end of while loop
            exit();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }//end of catch
    }//end of run

    private int printMenuAndRetrieveSelection() {
        //Return user's selected menu option
        return view.displayMenuAndPromptForSelection();
    }

    private void displayOrdersOrdersByDate() throws FlooringPersistenceException {
        //Prompt user for order date and store as orderDate
        LocalDate orderDate = view.promptForDate();
        try {
            //Pass order date to service layer
            List<Order> orders = service.retrieveAllOrdersByDate(orderDate);
            //Pass orders for specified order date to view
            view.displayOrdersByDate(orders);
            //Catch order not found exception
        } catch (OrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void addNewOrder() throws FlooringPersistenceException {
        //Call to service to get product object
        List<Product> availableProducts = service.retrieveAllProducts();
        //Call to service to get tax object
        List<Tax> availableTax = service.retrieveAllTaxes();
        //List all available product information to user
        view.displayAllProducts(availableProducts);
        //List all available tax information to user
        view.displayAllTaxes(availableTax);
        //Prompt user for order date and store as orderDate
        LocalDate orderDate = view.promptForDate();
        //Prompt user for new order details
        Order order = view.promptForNewOrderDetails();
        //Set order date on new order
        order.setOrderDate(orderDate);
        //Display order summary to user
        view.displayOrderSummary(order);
        try { //Try
            //Prompt to commit order summary to memory and store in userResponse
            boolean userResponse = view.promptToCommitToMemory();
            if (userResponse == true) {
                //Pass new order to service layer
                service.addOrder(order);
                //Order successfully created banner
                view.bannerOrderSuccessfullyCreated();
            } else {
                //Order deleted banner
                view.bannerOrderTrashed();
            }
            //Catch state not found and Material not found exception
        } catch (TaxStateNotFoundException | ProductMaterialNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void removeOrder() throws FlooringPersistenceException {

        try {
            //Prompt user for order date
            LocalDate orderDate = view.promptForDate();
            //Prompt user for order ID
            String orderId = view.promptForOrderId();
            //Pass order date and order ID to service
            Order order = service.retrieveOrderByDateAndId(orderDate, orderId);
            //Display order summary to user
            view.displayOrderSummary(order);
            //Prompt commit remove order
            boolean userResponse = view.promptToCommitToMemory();
            if (userResponse == true) {
                //Pass order date and order id to service layer
                service.removeOrder(orderDate, orderId);
                //Display order removed successfully banner
                view.bannerRemovedOrderSuccessfully();
            } else {
                //Display order has not been removed banner
                view.bannerOrderWillNotBeRemoved();
            }
            //Catch order not found exception
        } catch (OrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void editOrder() throws FlooringPersistenceException {

        try {
            //Prompt user for order date
            //Pass down orderDate note*** for edit date
            LocalDate orderDate = view.promptForDate();
            //Prompt user for order ID
            String orderId = view.promptForOrderId();
            //Pass order date and order ID to service
            Order order = service.retrieveOrderByDateAndId(orderDate, orderId);
            //Pass order to view for order updates
            Order updatedOrder = view.promptForOrderUpdate(order);
            //Pass order date and order id to service layer
            service.editOrder(orderDate, updatedOrder);
            //Catch exceptions and display error message depending on exception
        } catch (OrderNotFoundException | TaxStateNotFoundException | ProductMaterialNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void saveAllOrders() throws FlooringPersistenceException {
        //Call to service to allow user to Save
        boolean userResponse = view.promptForSaveAllOrders();
        //If true pass to service layer
        if (userResponse == true) {
            service.saveAllOrders();
            //Display save success banner
            view.bannerSavedOrderSuccesfully();
        } else
            //Display order not save cancelled
            view.bannerOrderNotSaved();
    }

    private void unknownCommand() {
        //Display unknown command banner
        view.bannerUnknownCommand();
    }

    private void trainingMode() {
        //Prompt user to enter Training or Production
        boolean enterTrainingMode = view.promptToEnterTrainingMode();

        //Pass to service
        if (enterTrainingMode == true) {
            //activate training
            service.activateTrainingMode(enterTrainingMode);

            view.bannerEnteredTrainingMode();
        } else {
            service.activateTrainingMode(enterTrainingMode);
            view.bannerProductionMode();
        }
    }

    private void exit() {
        //Display exit
        view.bannerExit();
    }
}//end of controller class
