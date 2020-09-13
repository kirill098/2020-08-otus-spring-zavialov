package service.impl;

import lombok.RequiredArgsConstructor;
import model.Cell;
import service.InputService;
import service.OutputService;
import service.TestService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final InputService inputService;
    private final OutputService outputService;

    @Override
    public void start() throws IOException {
        List<Cell> onlyQuestions = inputService.readCells();
        List<Cell> questionsAndAnswers = outputService.ask(onlyQuestions);

        long countRightAnswers = questionsAndAnswers.stream()
                .filter(c -> c.getExpectedAnswer().equalsIgnoreCase(c.getActualAnswer()))
                .count();
        outputService.result(countRightAnswers);
    }
}
