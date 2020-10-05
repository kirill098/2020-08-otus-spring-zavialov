package ru.otus.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Scanner;

@Configuration
public class OutputServiceConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
