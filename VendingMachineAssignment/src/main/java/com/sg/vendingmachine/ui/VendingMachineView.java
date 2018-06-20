package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.VendingMachineChange;
import com.sg.vendingmachine.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    private UserIO Io;

    public VendingMachineView(UserIO Io) {
        this.Io = Io;
    }

    public void displayWelcomeBanner() {
        Io.print("=================================================\n\t\t\t\t\tWELCOME\n=================================================");
    }

    public void displayVendingMachineHeader() {
        Io.print("ID      " + "Snack Name      " + "Snack Price      " + "Quantity      ");
    }

    public int displayMenuAndPromptSelection() {
        Io.print("=================================================");
        Io.print("Menu Items:");
        Io.print("1. Add Money");
        Io.print("2. Purchase an Item");
        Io.print("3. Get Change");
        Io.print("4. Exit");

        return Io.readInt("Please select from the above", 1,4);
    }

    public void displayVendingMachineItemInventory (List<VendingMachineItem> vendingMachineItem) {

        for(VendingMachineItem currentItem : vendingMachineItem) {
            if (currentItem.getQuantity() > 0) {
                Io.print(currentItem.getItemId() + "        " +
                        currentItem.getName() + "            " + currentItem.getPrice() + "             " + currentItem.getQuantity());
            }
        }
    }

    public BigDecimal promptForMoneyToAdd() {

        return Io.readBigDecimal("Please add either:\n $1.00 \n $5.00 \n $10.00 ");
    }

    public String promptForItemId() {

        return Io.readString("Please enter the ID of the snack you want to purchase");
    }

    public void displayChange(VendingMachineChange moneyInChange) {

        Io.print("Your change includes:" + "\n--" + moneyInChange.getNumQuarters() + " Quarters--" +
                "\n--" + moneyInChange.getNumDimes() + " Dimes--" + "\n--" + moneyInChange.getNumNickels() +" Nickels--" +
                "\n--" + moneyInChange.getNumPennies() +" Pennies--");


    }

    public void displayItem (VendingMachineItem itemPurchased) {

        Io.print("You just purchased" + itemPurchased.getName());

    }

    public void displayCurrentBalance(BigDecimal currentBalance) {

        Io.print("Your Current Balance is " + "----" + currentBalance + "----");
    }

    public void displayErrorMessage(String errorMsg) {
        Io.print("======Error======");
        Io.print(errorMsg);
    }

    public void displayErrorMessageItemUnavailable(String errorMsg) {
        //Io.print("Item Unavailable");
        Io.print(errorMsg);
    }

}
