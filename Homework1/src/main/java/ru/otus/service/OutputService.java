package ru.otus.service;

import ru.otus.model.Cell;

import java.util.List;

public interface OutputService {

    List<Cell> ask(List<Cell> cells);

    void getConclusionMsg(long count);

    void getGreetingMsg();
}
