package ru.otus.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.Comment;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {

}
