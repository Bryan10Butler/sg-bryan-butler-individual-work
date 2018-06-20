import com.sg.dvdcollection.controller.DvdController;
import com.sg.dvdcollection.dao.DvdDao;
import com.sg.dvdcollection.dao.DvdDaoFileImpl;
import com.sg.dvdcollection.ui.DvdView;
import com.sg.dvdcollection.ui.UserIO;
import com.sg.dvdcollection.ui.UserIOConsoleImpl;

public class App {

    //Invoking method run within our main
    public static void main(String[] args) {

        UserIO myIo= new UserIOConsoleImpl();
        DvdView myView = new DvdView(myIo);
        DvdDao myDao = new DvdDaoFileImpl();
        DvdController controller = new DvdController(myDao, myView);
        controller.run();
    }//end of main

}
