package ru.otus.dao;

import ru.otus.model.Author;

import java.util.List;

public interface AuthorDao {

    long insert(Author author);
    Author getById(long id);
    Author getByName(String name);
    List<Author> getAll();
}