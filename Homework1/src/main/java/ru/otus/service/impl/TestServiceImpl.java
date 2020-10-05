package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.model.Cell;
import ru.otus.service.InputService;
import ru.otus.service.OutputService;
import ru.otus.service.TestService;

import java.io.IOException;
import java.util.List;

@Service("testService")
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final InputService inputService;
    private final OutputService outputService;

    @Override
    public void start() throws IOException {
        outputService.getGreetingMsg();
        List<Cell> onlyQuestions = inputService.readCells();
        List<Cell> questionsAndAnswers = outputService.ask(onlyQuestions);
        long countRightAnswers = questionsAndAnswers.stream()
                .filter(c -> c.getExpectedAnswer().equalsIgnoreCase(c.getActualAnswer()))
                .count();
        outputService.getConclusionMsg(countRightAnswers);
    }
}
