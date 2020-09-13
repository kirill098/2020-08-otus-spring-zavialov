package service.impl;

import lombok.NoArgsConstructor;
import model.Cell;
import service.OutputService;

import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
public class OutputServiceImpl implements OutputService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<Cell> ask(List<Cell> cells) {
        return cells.stream().map(cell -> {
            System.out.print(cell.getQuestion() + "=");
            cell.setActualAnswer(scanner.next());
            return cell;
        }).collect(toList());
    }

    @Override
    public void result(long count) {
        System.out.println(String.format("Dear user, you answered %s of 5 questions correctly!", count));
    }
}
