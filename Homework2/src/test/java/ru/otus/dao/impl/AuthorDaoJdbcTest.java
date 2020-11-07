package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.model.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final String INSERTED_NAME = "name";
    private static final long INSERTED_ID = 3;

    private static final String EXISTED_NAME = "author1";
    private static final long EXISTED_ID = 1;

    private static final long EXPECTED_COUNT = 2;

    @Autowired
    private AuthorDaoJdbc authorDao;

    @DisplayName("корректно сохранять автора")
    @Test
    void insert() {
        Author expected = new Author(INSERTED_ID, INSERTED_NAME);
        long id = authorDao.insert(expected);
        Author actual = authorDao.getById(id);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно извлекать автора по id")
    @Test
    void getById() {
        Author expected = new Author(EXISTED_ID, EXISTED_NAME);
        Author actual = authorDao.getById(EXISTED_ID);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно извлекать автора по name")
    @Test
    void getByName() {
        Author expected = new Author(EXISTED_ID, EXISTED_NAME);
        Author actual = authorDao.getByName(EXISTED_NAME);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно извлекать всех авторов")
    @Test
    void getAll() {
        int count = authorDao.getAll().size();
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }
}