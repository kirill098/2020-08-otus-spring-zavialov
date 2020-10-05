package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class InputServiceConfig {

    @Value(value = "${file.path}")
    private String pathToFile;

    @Bean
    public InputStreamReader reader() throws IOException {
        return new InputStreamReader(new FileInputStream(pathToFile));
    }
}
