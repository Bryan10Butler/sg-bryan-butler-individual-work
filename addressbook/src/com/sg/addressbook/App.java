package com.sg.addressbook;

import com.sg.addressbook.controller.AddressBookController;

public class App {

    public static void main(String[] args) {
        AddressBookController controller = new AddressBookController();

        controller.run();
    }
}
