package ru.otus.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    void deleteById(long id);
}
