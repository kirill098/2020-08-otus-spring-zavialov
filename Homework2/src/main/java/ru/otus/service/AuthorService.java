package ru.otus.service;

import ru.otus.model.Author;

import java.util.List;

public interface AuthorService {

    Author create(String name);
    List<Author> getAll();
}
