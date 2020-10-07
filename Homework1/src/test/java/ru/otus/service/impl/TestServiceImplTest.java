package ru.otus.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.service.InputService;
import ru.otus.service.OutputService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("Класс TestServiceImplTest")
@SpringBootTest
class TestServiceImplTest {

    @TestConfiguration
    static class TestServiceConfiguration {
        @Bean
        public OutputService outputService() {
            OutputService outputService = mock(OutputService.class);
            when(outputService.ask(any())).thenReturn(Collections.emptyList());
            return outputService;
        }

        @SneakyThrows
        @Bean
        public InputService inputService() {
            InputService inputService = mock(InputService.class);
            when(inputService.readCells()).thenReturn(Collections.emptyList());
            return inputService;
        }
    }

    @Autowired
    private TestServiceImpl testService;

    @DisplayName("метод start")
    @Test
    void shouldCorrectStart() {
        assertThatNoException().isThrownBy(() -> testService.start());
    }
}