package ru.otus.dao;

import ru.otus.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book save(Book book);

    Optional<Book> findById(long id);
    Optional<Book> findByTitle(String title);
    List<Book> findAll();

    void updateTitleById(long id, String title);
    void deleteById(long id);
}
