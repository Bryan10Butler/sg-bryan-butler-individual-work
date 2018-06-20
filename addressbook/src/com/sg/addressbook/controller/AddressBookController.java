package com.sg.addressbook.controller;

import com.sg.addressbook.ui.UserIO;
import com.sg.addressbook.ui.UserIOConsoleImpl;

public class AddressBookController {

    //instantiate by creating instance io
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        while (keepGoing) {
            io.print("Main Menu");
            io.print("1. Add Address");
            io.print("2. Delete Address");
            io.print("3. Find Address");
            io.print("4. List Address Count");
            io.print("5. List All Addresses");
            io.print("6. Exit");

            menuSelection = io.readInt("Please select from the " +
                    "above choices",1,6);

            switch(menuSelection) {

                case 1:
                    io.print("ADD ADDRESS");
                    break;
                case 2:
                    io.print("DELETE ADDRESS");
                    break;
                case 3:
                    io.print("FIND ADDRESS");
                    break;
                case 4:
                    io.print("LIST ADDRESS COUNT");
                    break;
                case 5:
                    io.print("LIST ALL ADDRESSES");
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }//end of switch
        }//end of while
        io.print("GOOD BYE");
    }//end of run
}//end of public class Address Book Controller
