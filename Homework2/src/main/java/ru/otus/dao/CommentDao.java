package ru.otus.dao;

import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);
    Optional<Comment> findByDescription(String description);
    List<Comment> findAll();

    void updateDescriptionById(long id, String description);
    void deleteById(long id);
}
