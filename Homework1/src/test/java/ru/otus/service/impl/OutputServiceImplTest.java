package ru.otus.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.model.Cell;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс OutputServiceImpl")
@ExtendWith(MockitoExtension.class)
class OutputServiceImplTest {

    private static final String TEST_QUESTION = "test_question";
    private static final String TEST_EXPECTED_ANSWER = "test_expected_answer";
    private static final String TEST_ACTUAL_ANSWER = "test_actual_answer";
    private static final long TEST_RESULT = 5;

    private OutputServiceImpl outputService;

    @DisplayName("метод result")
    @Test
    void shouldCorrectOutputResult() {
        outputService = new OutputServiceImpl(mock(Scanner.class));
        assertThatNoException().isThrownBy(() ->
                outputService.result(TEST_RESULT));
    }

    @DisplayName("метод ask")
    @Test
    void shouldCorrectAsk() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.next()).thenReturn(TEST_ACTUAL_ANSWER);
        outputService = new OutputServiceImpl(scanner);

        List<Cell> cells = List.of(Cell.builder()
                .question(TEST_QUESTION)
                .expectedAnswer(TEST_EXPECTED_ANSWER)
                .build());

        Assertions.assertThat(outputService.ask(cells))
                .isNotNull()
                .hasSize(1)
                .extracting(Cell::getActualAnswer)
                .isEqualTo(List.of(TEST_ACTUAL_ANSWER));
    }
}