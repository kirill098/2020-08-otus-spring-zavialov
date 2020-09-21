package ru.otus.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.service.InputService;
import ru.otus.service.OutputService;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("Класс TestServiceImplTest")
@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    private TestServiceImpl testService;

    @DisplayName("метод start")
    @Test
    void shouldCorrectStart() throws IOException {
        OutputService outputService = mock(OutputService.class);
        when(outputService.ask(any())).thenReturn(Collections.emptyList());

        InputService inputService = mock(InputService.class);
        when(inputService.readCells()).thenReturn(Collections.emptyList());

        testService = new TestServiceImpl(inputService, outputService);
        assertThatNoException().isThrownBy(() -> testService.start());
    }

}