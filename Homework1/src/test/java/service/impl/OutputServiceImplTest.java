package service.impl;

import model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.OutputService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("Класс OutputServiceImpl")
@ExtendWith(MockitoExtension.class)
class OutputServiceImplTest {

    private static final String TEST_QUESTION = "test_question";
    private static final String TEST_EXPECTED_ANSWER = "test_expected_answer";
    private static final String TEST_ACTUAL_ANSWER = "test_actual_answer";
    private static final long TEST_RESULT = 5;

    private OutputService outputService;

    @BeforeEach
    void init() {
        this.outputService = new OutputServiceImpl();
    }

    @DisplayName("метод result")
    @Test
    void shouldCorrectOutputResult() {
        assertThatNoException().isThrownBy(() ->
                outputService.result(TEST_RESULT));
    }

    @DisplayName("метод ask")
    @Test
    void shouldCorrectAsk() {
        List<Cell> cells = newCells();
        //todo
    }

    private List<Cell> newCells() {
        Cell cell = new Cell();
        cell.setQuestion(TEST_QUESTION);
        cell.setActualAnswer(TEST_ACTUAL_ANSWER);
        cell.setExpectedAnswer(TEST_EXPECTED_ANSWER);

        List<Cell> cells = new ArrayList<>();
        cells.add(cell);
        return cells;
    }
}