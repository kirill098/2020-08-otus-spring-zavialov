package ru.otus.service;

import ru.otus.model.Book;
import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment create(Comment comment);

    Comment getById(long id);

    List<Comment> getAll();

    void updateDescriptionById(long id, String description);

    void deleteById(long id);
}
