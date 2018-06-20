package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlooringView {

    //Properties
    private UserIO Io;
    private static final String EMPTY_STRING = "";

    //Constructors
    public FlooringView(UserIO io) {
        Io = io;
    }

    //Display menu
    public int displayMenuAndPromptForSelection() {
        Io.print("1: Display Orders");
        Io.print("2: Add an Order");
        Io.print("3: Edit an Order");
        Io.print("4: Remove an Order");
        Io.print("5: Save Current Work");
        Io.print("6: Training Mode");
        Io.print("7: Quit");
        //Prompt user to select menu option
        return Io.readInt("====Please select from the above options====", 1, 7);
    }

    public LocalDate promptForDate() {
        //Prompt user to input an order date
        return Io.readLocalDate("====Please input an ORDER DATE of format MMDDYYYY====");
    }

    public String promptForOrderId() {
        //Prompt uer to input an order Id
        return Io.readString("Please enter Order Id");
    }

    public void displayOrdersByDate(List<Order> ordersByDate) {

        Io.print("====ORDER DETAILS====");
        //For all orders in OrdersByDate
        for (Order orders : ordersByDate) {

            //If orders > 0 continue to loop through list
            //if (orders.getOrderNumber() > 0) {

            Io.print("Name: " + orders.getCustomerName() +
                    "\nOrder Number: " + orders.getOrderNumber() +
                    "\nState: " + orders.getTaxObject().getState() +
                    "\nTax: " + orders.getTaxObject().getTaxRate() +
                    "\nProduct Material: " + orders.getProductObject().getProductType() +
                    "\nLabor Cost Per Square Foot: " + orders.getProductObject().getLabCostPerSquareFoot() +
                    "\nMaterial Cost Per Square Foot: " + orders.getProductObject().getMaterialCostPerSquareFoot() +
                    "\nArea: " + orders.getArea() +
                    "\nTotal Material Cost: " + orders.getTotalMaterialCost() +
                    "\nTotal Labor Cost: " + orders.getTotalLaborCost() +
                    "\nTotal Tax: " + orders.getTotalTax() +
                    "\nTotal Cost: " + orders.getTotalCost());
            //}
        }
        Io.print("====ORDER DETAILS====");
    }

    public Order promptForNewOrderDetails() {
        //Prompt user for new order details
        String customerName = Io.readString("Please input your NAME: ");
        String state = Io.readString("Please input STATE: ");
        String productMaterial = Io.readString("Please input PRODUCT MATERIAL: ");
        String area = Io.readString("Please input AREA");

        //Create instance newOrder of type Order
        Order newOrder = new Order();

        //Set user input on newOrder
        newOrder.setCustomerName(customerName);
        newOrder.setTaxObject(new Tax(state));
        newOrder.setProductObject(new Product(productMaterial));
        newOrder.setArea(new BigDecimal(area));

        return newOrder;
    }

    public void displayOrderSummary(Order orderSummary) {
        //Display order summary from user input
        Io.print("====ORDER SUMMARY====" +
                "\nName: " + orderSummary.getCustomerName() +
                "\nTax State: " + orderSummary.getTaxObject().getState() +
                "\nProduct Material: " + orderSummary.getProductObject().getProductType() +
                "\nArea: " + orderSummary.getArea() +
                "\n====ORDER SUMMARY====");
    }

    public void displayAllProducts(List<Product> productObject) {
        Io.print("====PRODUCT INFORMATION====");
        for (Product availableProducts : productObject) {
            Io.print(availableProducts.getProductType() +
                    "::Cost Per Square Foot::$" + availableProducts.getMaterialCostPerSquareFoot() +
                    "::Labor Cost Per Square Foot::$" + availableProducts.getLabCostPerSquareFoot());
        }
    }

    public void displayAllTaxes(List<Tax> TaxObject) {
        Io.print("\n====TAX INFORMATION====");
        for (Tax availableTax : TaxObject) {
            Io.print("::State::" + availableTax.getState() +
                    "::Tax Rate::$" + availableTax.getTaxRate());
        }
    }

    public boolean promptToCommitToMemory() {

        //Initialize orderLooksOkay to true
        boolean orderLooksOkay = true;
        //Prompt user for input if order looks correct
        String userResponse = Io.readString("Please confirm 'Yes' or 'No' to commit");

        //If user response is No, change Boolean value to false and cancel order
        if (userResponse.equals("No")) {
            orderLooksOkay = false;
            return orderLooksOkay;
            //Else return true and commit order to memory
        } else {
            return orderLooksOkay;
        }//end of else
    }

    public void bannerOrderTrashed() {
        Io.print("====Order has been deleted====");
    }

    public void bannerOrderSuccessfullyCreated() {
        Io.print("====Order has been sucessfully created====");
    }

    public void bannerRemovedOrderSuccessfully() {
        Io.print("====Order has been successfully removed====");
    }

    public void bannerOrderWillNotBeRemoved() {
        Io.print("====Order has not been cancelled====");
    }

    public Order promptForOrderUpdate(Order order) {

        //Get order details and display back to user for edit
        String customerName = Io.readString("Input new Customer Name: Current Name: " + order.getCustomerName());
        String state = Io.readString("Input new State: Current State: " + order.getTaxObject().getState());
        String material = Io.readString("Input new Material: Current Material: " + order.getProductObject().getProductType());
        String area = Io.readString("Input new Area: Current Area: " + order.getArea());
        String updatedDate = Io.readString("Input new Date: " + order.getOrderDate());

        boolean userInput = promptToCommitToMemory();
        if (userInput == true) {
            //set new order details per user input
            //If customerName is not an empty string, SET
            if (!EMPTY_STRING.equals(customerName)) {
                order.setCustomerName(customerName);
            }
            //If state is not an empty string, SET
            if (!EMPTY_STRING.equals(state)) {
                order.setTaxObject(new Tax(state));
            }
            //If material is not an empty string, SET
            if (!EMPTY_STRING.equals(material)) {
                order.setProductObject(new Product(material));
            }
            //If area is not an empty String, SET
            if (!EMPTY_STRING.equals(area)) {
                order.setArea(new BigDecimal(area));
            }
            //If date is not an empty String, SET
            if (!EMPTY_STRING.equals(updatedDate)) {
                order.setOrderDate(LocalDate.parse(updatedDate, DateTimeFormatter.ofPattern("MMddyyyy")));
            }
            bannerOrderUpdatedSuccessfully();
        }
        bannerOrderNotSaved();
        //Return order
        return order;
    }

    public void bannerOrderUpdatedSuccessfully() {
        Io.print("====Order has been updated successfully====");
    }

    public void bannerOrderWillNotBeUpdated() {
        Io.print("====Order has not been updated====");
    }

    public boolean promptForSaveAllOrders() {

        boolean wantToSaveOrder = true;

        String userResponse = Io.readString("Please confirm 'Yes' or 'No' to save current work");

        //If user response is No, change Boolean value to false and do not save
        if (userResponse.equals("No")) {
            wantToSaveOrder = false;
            return wantToSaveOrder;
            //Else return true and save order
        } else {
            return wantToSaveOrder;
        }//end of else
    }

    //Banner
    public void bannerSavedOrderSuccesfully() {
        Io.print("====Order saved successfully====");
    }

    //Banner
    public void bannerOrderNotSaved() {
        Io.print("====Order not saved====");
    }

    //Banner
    public void bannerUnknownCommand() {
        Io.print("Unknown Command");
    }

    //Banner
    public void bannerExit() {
        Io.print("====BYE====");
    }

    //Banner
    public void bannerEnteredTrainingMode() {
        Io.print("====Welcome to Training Mode====");
    }

    //Banner
    public void bannerProductionMode() {
        Io.print("====Welcome to Production====");
    }

    //Exception
    public void displayErrorMessage(String errorMsg) {
        Io.print(errorMsg);
    }

    public boolean promptToEnterTrainingMode() {

        boolean enterTraining;

        String userResponse = Io.readString("Confirm 'Training' or 'Production'");
        //If user input No
        if (userResponse.equals("Training")) {
            //Enter training
            enterTraining = true;
        } else {
            //Enter production
            enterTraining = false;
        }
        return enterTraining;
    }
}
