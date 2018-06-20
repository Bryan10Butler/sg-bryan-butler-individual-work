package com.sg.wirepractice.controller;

import com.sg.wirepractice.service.EmailContactsService;
import com.sg.wirepractice.ui.EmailContactsView;

public class EmailContactsController {

    //properties
    private EmailContactsService myService;

    private EmailContactsView myView;

    //constructor
    public EmailContactsController (EmailContactsService myService, EmailContactsView myView) {
        this.myService = myService;
        this.myView = myView;
    }

    public void run() {

    }//end of run


}
