package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.TestService;

@ShellComponent
@RequiredArgsConstructor
public class ShellApplication {

    private final TestService testService;

    @SneakyThrows
    @ShellMethod(value = "Start testing", key = {"s", "start"})
    public void start() {
        testService.start();
    }
}
