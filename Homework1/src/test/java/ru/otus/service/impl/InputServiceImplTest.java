package ru.otus.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.model.Cell;

import java.io.IOException;
import java.util.List;

@DisplayName("Класс InputServiceImplTest")
@SpringBootTest
class InputServiceImplTest {

    private static final String TEST_QUESTION = "test_question";
    private static final String TEST_EXPECTED_ANSWER = "test_expected_answer";

    @Autowired
    private InputServiceImpl inputService;

    @DisplayName("метод readCells")
    @Test
    void shouldCorrectReadCells() {
        Assertions.assertThat(inputService.readCells())
                .isNotNull()
                .hasSize(1)
                .isEqualTo(List.of(Cell.builder()
                        .question(TEST_QUESTION)
                        .expectedAnswer(TEST_EXPECTED_ANSWER)
                        .build())
                );
    }
}