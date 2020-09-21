package ru.otus.service.impl;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.model.Cell;
import ru.otus.service.InputService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InputServiceImpl implements InputService {

    private final InputStreamReader reader;

    public List<Cell> readCells() {
        CsvToBean csv = new CsvToBeanBuilder<Cell>(reader)
                .withSeparator('=')
                .withMappingStrategy(setColumnMapping())
                .build();
        return csv.parse();
    }

    private static ColumnPositionMappingStrategy setColumnMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Cell.class);
        strategy.setColumnMapping(new String[]{"question", "expectedAnswer"});
        return strategy;
    }
}
