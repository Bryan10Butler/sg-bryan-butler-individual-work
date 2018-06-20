import com.sg.baseballleague.controller.BaseballController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BaseballController myController = ctx.getBean("myController", BaseballController.class);
        //calling run method within controller
        myController.run();
    }

}
