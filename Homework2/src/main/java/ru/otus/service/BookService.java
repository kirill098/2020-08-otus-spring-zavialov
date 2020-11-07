package ru.otus.service;

import ru.otus.model.Book;

import javax.validation.Valid;
import java.util.List;

public interface BookService {

    Book create(@Valid Book book);
    Book getBookById(long id);
    List<Book> getAllBooks();
    Book updateBook(@Valid Book book);
    void deleteBookById(long id);
}
