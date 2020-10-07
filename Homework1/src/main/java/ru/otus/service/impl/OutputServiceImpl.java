package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.model.Cell;
import ru.otus.service.OutputService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

@Service
public class OutputServiceImpl implements OutputService {

    private final Scanner scanner;
    private final MessageSource messageSource;
    private final Locale locale;

    public OutputServiceImpl(Scanner scanner, MessageSource messageSource, @Value(value = "${msg.language}") String locale) {
        this.scanner = scanner;
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public List<Cell> ask(List<Cell> cells) {
        return cells.stream().map(cell -> {
            System.out.print(cell.getQuestion() + "=");
            cell.setActualAnswer(scanner.next());
            return cell;
        }).collect(toList());
    }

    @Override
    public void getGreetingMsg() {
        System.out.println(messageSource.getMessage("msg.greeting", null, locale));
    }

    @Override
    public void getConclusionMsg(long count) {
        System.out.println(messageSource.getMessage("msg.conclusion", new String[] {Long.toString(count)}, locale));
    }
}
