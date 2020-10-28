package ru.otus.dao;

import ru.otus.model.Book;

import java.util.List;

public interface BookDao {

    long insert(Book book);
    Book getById(long id);
    List<Book> getAll();
    void update(Book book);
    void deleteById(long id);
}
