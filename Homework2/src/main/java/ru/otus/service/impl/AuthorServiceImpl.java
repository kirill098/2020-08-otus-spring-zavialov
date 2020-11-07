package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.otus.dao.AuthorDao;
import ru.otus.model.Author;
import ru.otus.service.AuthorService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Author create(@Valid Author author) {
        long id = authorDao.insert(author);
        return authorDao.getById(id);
    }

    @Override
    public Author ensure(@Valid Author author) {
        Author result = authorDao.getByName(author.getName());
        if (result == null) {
            long id = authorDao.insert(author);
            result = authorDao.getById(id);
        }
        return result;
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author getByName(@NotBlank String name) {
        return authorDao.getByName(name);
    }
}
