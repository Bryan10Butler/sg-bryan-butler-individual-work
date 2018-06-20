import controller.EmailContactsController;
import dao.EmailContactsDao;
import dao.EmailContactsDaoInMemoryImpl;
import service.EmailContactsService;
import service.EmailContactsServiceImpl;
import ui.EmailContactView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {

        UserIO myIO = new UserIOConsoleImpl();
        EmailContactView myView = new EmailContactView(myIO);

        EmailContactsDao myDao = new EmailContactsDaoInMemoryImpl();
        EmailContactsService myService = new EmailContactsServiceImpl(myDao);

        EmailContactsController myController = new EmailContactsController(myService, myView);


        myController.run();
    }
}
