package ru.otus.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.model.Cell;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс OutputServiceImpl")
@SpringBootTest
class OutputServiceImplTest {

    private static final String TEST_QUESTION = "test_question";
    private static final String TEST_EXPECTED_ANSWER = "test_expected_answer";
    private static final String TEST_ACTUAL_ANSWER = "test_actual_answer";
    private static final long TEST_RESULT = 5;

    @TestConfiguration
    static class OutputServiceConfiguration {
        @Bean
        public Scanner scanner() {
            Scanner scanner = mock(Scanner.class);
            when(scanner.next()).thenReturn(TEST_ACTUAL_ANSWER);
            return scanner;
        }
    }

    @Autowired
    private OutputServiceImpl outputService;

    @DisplayName("метод getConclusionMsg")
    @Test
    void shouldCorrectgGtConclusionMsg() {
        assertThatNoException().isThrownBy(() ->
                outputService.getConclusionMsg(TEST_RESULT));
    }

    @DisplayName("метод getGreetingMsg")
    @Test
    void shouldCorrectGetGreetingMsg() {
        assertThatNoException().isThrownBy(() ->
                outputService.getGreetingMsg());
    }

    @DisplayName("метод ask")
    @Test
    void shouldCorrectAsk() {
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