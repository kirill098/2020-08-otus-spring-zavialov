package ru.otus.dao;

import ru.otus.model.Genre;

import java.util.List;

public interface GenreDao {

    long insert(Genre genre);
    Genre getById(long id);
    List<Genre> getAll();
}
