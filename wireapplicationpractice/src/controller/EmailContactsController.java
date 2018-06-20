package controller;

import service.EmailContactsService;
import ui.EmailContactView;

public class EmailContactsController {

    // By adding these as properties of the class
    // I am saying the the controller depends on these two classes
    // So, the Controller has two dependencies... service and view
    private EmailContactsService myService;
    private EmailContactView myView;

    // This Constructor allows any class that creates an instance of the Controller
    // to pass in the values for the controllers dependencies
    // This allows for dependency injection
    public EmailContactsController(EmailContactsService myService, EmailContactView myView) {
        this.myService = myService;
        this.myView = myView;
    }

    public void run() {

        System.out.println("Hello");

    }
}
