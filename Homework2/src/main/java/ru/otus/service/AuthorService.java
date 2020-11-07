package ru.otus.service;

import ru.otus.model.Author;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface AuthorService {

    Author create(@Valid Author author);
    Author ensure(@Valid Author author);
    List<Author> getAll();
    Author getById(long id);
    Author getByName(@NotBlank String name);
}
