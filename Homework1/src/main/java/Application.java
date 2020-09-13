import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TestService;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");
        TestService testService = context.getBean("testService", TestService.class);
        testService.start();
    }

}
