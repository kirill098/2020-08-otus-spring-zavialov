package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final String INSERTED_NAME = "name";
    private static final long INSERTED_ID = 3;

    private static final String EXISTED_NAME = "genre1";
    private static final long EXISTED_ID = 1;

    private static final long EXPECTED_COUNT = 2;

    @Autowired
    private GenreDaoJdbc genreDao;

    @DisplayName("корректно сохранять жанр")
    @Test
    void insert() {
        Genre expected = new Genre(INSERTED_ID, INSERTED_NAME);
        long id = genreDao.insert(expected);
        Genre actual = genreDao.getById(id);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно извлекать жанр по id")
    @Test
    void getById() {
        Genre expected = new Genre(EXISTED_ID, EXISTED_NAME);
        Genre actual = genreDao.getById(EXISTED_ID);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно извлекать все жанры")
    @Test
    void getAll() {
        int count = genreDao.getAll().size();
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }
}