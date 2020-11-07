package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.otus.dao.GenreDao;
import ru.otus.model.Genre;
import ru.otus.service.GenreService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Genre create(@Valid Genre genre) {
        long id = genreDao.insert(genre);
        return genreDao.getById(id);
    }

    @Override
    public Genre ensure(@Valid Genre genre) {
        Genre result = genreDao.getByName(genre.getName());
        if (result == null) {
            long id = genreDao.insert(genre);
            result = genreDao.getById(id);
        }
        return result;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre getByName(@NotBlank String name) {
        return genreDao.getByName(name);
    }
}
