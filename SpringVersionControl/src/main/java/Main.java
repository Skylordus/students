import com.yberdaliyev.spring.AppConfig;
import com.yberdaliyev.spring.DataHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yerlan on 01.03.2017.
 */
public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

        DataHandler dataHandler = (DataHandler) ctx.getBean("dataHandler");
        //DataHandler dataHandler = new DataHandler();
        dataHandler.hadleData("","");
    }
}
