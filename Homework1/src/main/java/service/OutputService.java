package service;

import model.Cell;

import java.util.List;

public interface OutputService {

    List<Cell> ask(List<Cell> cells);

    void result(long count);
}
