package ru.otus.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Класс Cell")
class CellTest {

    private static final String TEST_QUESTION = "test_question";
    private static final String TEST_EXPECTED_ANSWER = "test_expected_answer";
    private static final String TEST_ACTUAL_ANSWER = "test_actual_answer";

    @DisplayName("корректно создается конструктором без параметров")
    @Test
    void shouldHaveCorrectConstructor() {
        Cell cell = new Cell();
        assertThat(cell).isNotNull();
    }

    @DisplayName("должен корректно записывать вопрос")
    @Test
    void shouldCorrectSetQuestion() {
        Cell cell = new Cell();
        cell.setQuestion(TEST_QUESTION);
        assertThat(cell.getQuestion()).isEqualTo(TEST_QUESTION);
    }

    @DisplayName("должен корректно записывать ожидаемый ответ")
    @Test
    void shouldCorrectSetExpectedAnswer() {
        Cell cell = new Cell();
        cell.setExpectedAnswer(TEST_EXPECTED_ANSWER);
        assertThat(cell.getExpectedAnswer()).isEqualTo(TEST_EXPECTED_ANSWER);
    }

    @DisplayName("должен корректно записывать фактический ответ")
    @Test
    void shouldCorrectSetActualAnswer() {
        Cell cell = new Cell();
        cell.setActualAnswer(TEST_ACTUAL_ANSWER);
        assertThat(cell.getActualAnswer()).isEqualTo(TEST_ACTUAL_ANSWER);
    }
}