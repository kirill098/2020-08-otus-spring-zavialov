package ru.otus.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.model.Cell;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@DisplayName("Класс InputServiceImplTest")
@ExtendWith(MockitoExtension.class)
class InputServiceImplTest {

    private static final String PATH_TO_FILE = "src/test/resources/test_questions.csv";
    private static final String TEST_QUESTION = "test_question";
    private static final String TEST_EXPECTED_ANSWER = "test_expected_answer";

    private InputServiceImpl inputService;

    @DisplayName("метод readCells")
    @Test
    void shouldCorrectReadCells() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(PATH_TO_FILE);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        inputService = new InputServiceImpl(reader);

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