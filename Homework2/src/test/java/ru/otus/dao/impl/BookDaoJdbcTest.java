package ru.otus.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.model.dao.BookDb;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final String INSERTED_TITLE = "title";
    private static final long INSERTED_AUTHOR_ID = 1l;
    private static final long INSERTED_GENRE_ID = 1l;
    private static final long INSERTED_ID = 3;

    private static final String EXISTED_TITLE = "title1";
    private static final long EXISTED_AUTHOR_ID = 1l;
    private static final long EXISTED_GENRE_ID = 1l;
    private static final long EXISTED_ID = 1;

    private static final long EXPECTED_COUNT = 2;

    @Autowired
    private BookDaoJdbc bookDao;

    @DisplayName("корректно сохранять книгу")
    @Test
    void insert() {
        BookDb expected = new BookDb(INSERTED_ID, INSERTED_TITLE, INSERTED_GENRE_ID, INSERTED_AUTHOR_ID);
        long id = bookDao.insert(expected);
        BookDb actual = bookDao.getById(id);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно извлекать книгу по id")
    @Test
    void getById() {
        BookDb expected = new BookDb(EXISTED_ID, EXISTED_TITLE, EXISTED_GENRE_ID, EXISTED_AUTHOR_ID);
        BookDb actual = bookDao.getById(EXISTED_ID);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно извлекать все книги")
    @Test
    void getAll() {
        int count = bookDao.getAll().size();
        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }

    @DisplayName("корректно обновлять книгу по id")
    @Test
    void update() {
        BookDb expected = new BookDb(EXISTED_ID, INSERTED_TITLE, INSERTED_GENRE_ID, INSERTED_AUTHOR_ID);
        bookDao.update(expected);
        BookDb actual = bookDao.getById(expected.getId());
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("корректно удалять книгу по id")
    @Test
    void deleteById() {
        bookDao.deleteById(EXISTED_ID);
        Assertions.assertNull(bookDao.getById(EXISTED_ID));
    }
}