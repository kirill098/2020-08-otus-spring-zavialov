package ru.otus.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
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
    private static final String TEST_LOCALE_EN = "en";

    private OutputServiceImpl outputService;

    @DisplayName("метод getConclusionMsg")
    @Test
    void shouldCorrectgGtConclusionMsg() {
        outputService = new OutputServiceImpl(mock(Scanner.class), mock(MessageSource.class), TEST_LOCALE_EN);
        assertThatNoException().isThrownBy(() ->
                outputService.getConclusionMsg(TEST_RESULT));
    }

    @DisplayName("метод getGreetingMsg")
    @Test
    void shouldCorrectGetGreetingMsg() {
        outputService = new OutputServiceImpl(mock(Scanner.class), mock(MessageSource.class), TEST_LOCALE_EN);
        assertThatNoException().isThrownBy(() ->
                outputService.getGreetingMsg());
    }

    @DisplayName("метод ask")
    @Test
    void shouldCorrectAsk() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.next()).thenReturn(TEST_ACTUAL_ANSWER);
        outputService = new OutputServiceImpl(scanner, mock(MessageSource.class), TEST_LOCALE_EN);

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