package ru.otus.service;

import ru.otus.model.Cell;

import java.io.IOException;
import java.util.List;

public interface InputService {

    List<Cell> readCells() throws IOException;
}
