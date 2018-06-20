package com.sg.dvdcollection;

import com.sg.dvdcollection.Service.DvdServiceLayer;
import com.sg.dvdcollection.Service.DvdServiceLayerImpl;
import com.sg.dvdcollection.controller.DvdController;
import com.sg.dvdcollection.dao.DvdAuditDao;
import com.sg.dvdcollection.dao.DvdAuditDaoFileImpl;
import com.sg.dvdcollection.dao.DvdDao;
import com.sg.dvdcollection.dao.DvdDaoFileImpl;
import com.sg.dvdcollection.ui.DvdView;
import com.sg.dvdcollection.ui.UserIO;
import com.sg.dvdcollection.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    //Invoking method run within our main
    public static void main(String[] args) {

        // Instantiate the UserIO implementation
        //UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        //DvdView myView = new DvdView(myIo);
        // Instantiate the DAO
        //DvdDao myDao = new DvdDaoFileImpl();
        // Instantiate the Audit DAO
        //DvdAuditDao myAuditDao = new DvdAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        //DvdServiceLayer myService = new DvdServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        //DvdController controller = new DvdController(myService, myView);
        // Kick off the Controller
        //controller.run();

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        DvdController controller =
                ctx.getBean("controller", DvdController.class);
        controller.run();

    }//end of main

}
