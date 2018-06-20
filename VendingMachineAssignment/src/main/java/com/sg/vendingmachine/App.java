package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.service.VendingMachineServiceFileImpl;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException{
        //Wiring up the application
        //Create instances of each dependency and inject
        //inject them into the class that depends on it

        //view depends on io
        //UserIO myIO = new UserIOConsoleImpl();
        //VendingMachineView myView = new VendingMachineView(myIO);

        //service depends on dao
        //create text file Prod and Test example FILENAME PROD and TEST
        //this allows to switch between Test and Prod depending on what you are doing
        //VendingMachineDao myDao = new VendingMachineDaoFileImpl("src/main/Prod.txt");
        //VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        //VendingMachineServiceLayer myService = new VendingMachineServiceFileImpl(myDao, myAuditDao);

        //controller depends on service and view
        //VendingMachineController myController = new VendingMachineController(myService, myView);
        //myController.run();

        //Application Context holds what we defined
        //Controller is the entry point hence why we are specifying it here
        //Dependent on the Service and the View
        //Application Context is an interface
        ApplicationContext ctx =
                //implementation we use
                //Class Path takes a string filename (one advantage)
                new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller =
                //get bean to retrieve beans associated with applicationContext
                //first parameter is if the id
                //second parameter is the type of bean you want to retrieve
                ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
