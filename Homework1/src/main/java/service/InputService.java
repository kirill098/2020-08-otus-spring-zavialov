package service;

import model.Cell;

import java.io.IOException;
import java.util.List;

public interface InputService {

    List<Cell> readCells() throws IOException;
}
