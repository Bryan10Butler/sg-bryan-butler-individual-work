package com.sg.wirepractice;

import com.sg.wirepractice.controller.EmailContactsController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        //create a  new instance of ApplicationContext object

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //create a new instance of EmailContactsController to all getBean
        EmailContactsController controller = ctx.getBean("controller", EmailContactsController.class);

        controller.run();

    }
}
