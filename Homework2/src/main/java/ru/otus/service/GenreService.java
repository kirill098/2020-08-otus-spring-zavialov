package ru.otus.service;

import ru.otus.model.Genre;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface GenreService {

    Genre create(@Valid Genre genre);
    Genre ensure(@Valid Genre genre);
    List<Genre> getAll();
    Genre getById(long id);
    Genre getByName(@NotBlank String name);
}
