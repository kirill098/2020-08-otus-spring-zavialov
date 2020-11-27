package ru.otus.service;

import ru.otus.dto.BookDescription;
import ru.otus.dto.SimpleBook;
import ru.otus.model.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);

    SimpleBook getById(long id);

    List<BookDescription> getAll();

    void updateTitleById(long id, String title);

    void deleteById(long id);
}
