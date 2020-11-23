package ru.otus.service;

import ru.otus.dto.SimpleBook;
import ru.otus.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book create(Book book);

    SimpleBook getById(long id);

    List<SimpleBook> getAll();

    void updateTitleById(long id, String title);

    void deleteById(long id);
}
