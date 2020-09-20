package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
@PropertySource("classpath:application.properties")
public class InputServiceConfig {

    @Value(value = "${file.path}")
    private String pathToFile;

    @Bean
    public InputStreamReader reader() throws IOException {
        return new InputStreamReader(new FileInputStream(pathToFile));
    }
}
