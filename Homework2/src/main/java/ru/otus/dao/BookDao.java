package ru.otus.dao;

import ru.otus.model.dao.BookDb;

import java.util.List;

public interface BookDao {

    long insert(BookDb book);
    BookDb getById(long id);
    List<BookDb> getAll();
    void update(BookDb book);
    void deleteById(long id);
}
