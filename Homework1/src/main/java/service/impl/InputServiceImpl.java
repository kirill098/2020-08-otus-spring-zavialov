package service.impl;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import model.Cell;
import service.InputService;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class InputServiceImpl implements InputService {

    private final String pathToFile;

    public List<Cell> readCells() throws IOException {
        CsvToBean csv = new CsvToBeanBuilder<Cell>(new FileReader(pathToFile))
                .withSeparator('=')
                .withMappingStrategy(setColumnMapping())
                .build();
        return csv.parse();
    }

    private static ColumnPositionMappingStrategy setColumnMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Cell.class);
        strategy.setColumnMapping(new String[] {"question", "expectedAnswer"});
        return strategy;
    }
}
