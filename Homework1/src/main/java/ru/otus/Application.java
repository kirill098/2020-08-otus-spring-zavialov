package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.TestService;

import java.io.IOException;

@ComponentScan
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        TestService testService = ctx.getBean(TestService.class);
        testService.start();
    }
}