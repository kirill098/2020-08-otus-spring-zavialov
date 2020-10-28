package ru.otus.service;

import ru.otus.model.Genre;

import java.util.List;

public interface GenreService {

    Genre create(String name);
    List<Genre> getAll();
}
