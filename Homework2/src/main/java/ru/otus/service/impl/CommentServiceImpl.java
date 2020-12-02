package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.CommentDao;
import ru.otus.model.Comment;
import ru.otus.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Transactional
    @Override
    public Comment create(Comment comment) {
        return commentDao.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(long id) {
        return commentDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll() {
        return (List)commentDao.findAll();
    }

    @Transactional
    @Override
    public void updateDescriptionById(long id, String description) {
        commentDao.findById(id).ifPresent(val -> val.setDescription(description));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentDao.deleteById(id);
    }
}
