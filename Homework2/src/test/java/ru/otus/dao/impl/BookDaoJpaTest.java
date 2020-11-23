package ru.otus.dao.impl;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами должно")
@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    private static final String AUTHOR_NAME = "authorName";
    private static final String GENRE_NAME = "genreName";
    private static final String BOOK_TITLE = "title";
    private static final String COMMENT = "comment";

    private static final String EXPECTED_BOOK_TITLE = "title_01";
    private static final int EXPECTED_QUERIES_COUNT = 4;
    private static final long EXPECTED_BOOK_ID = 1l;
    private static final int EXPECTED_SIZE = 3;

    @Autowired
    private BookDaoJpa bookDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("корректно сохранять книгу")
    @Test
    void createBook() {
        val authors = Collections.singletonList(new Author(0, AUTHOR_NAME));
        val genres = Collections.singletonList(new Genre(0, GENRE_NAME));
        val comments = Collections.singletonList(new Comment(0, COMMENT));

        val expected = new Book(0, BOOK_TITLE, authors, genres, comments);

        bookDao.save(expected);
        assertThat(expected.getId()).isGreaterThan(0);

        val actual = em.find(Book.class, expected.getId());
        assertThat(actual).isNotNull()
                .matches(b -> b.getId() > 0)
                .matches(b -> !b.getTitle().isBlank())
                .matches(b -> b.getAuthors() != null && b.getAuthors().size() > 0 && b.getAuthors().get(0).getId() > 0)
                .matches(b -> b.getGenres() != null && b.getGenres().size() > 0 && b.getGenres().get(0).getId() > 0)
                .matches(b -> b.getComments() != null && b.getComments().size() > 0 && b.getComments().get(0).getId() > 0);
    }

    @DisplayName("корректно извлекать книгу по id")
    @Test
    void getBookById() {
        val optionalActualBook = bookDao.findById(EXPECTED_BOOK_ID);
        val expectedBook = em.find(Book.class, EXPECTED_BOOK_ID);

        assertThat(optionalActualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @DisplayName("корректно извлекать все книги")
    @Test
    void getAllBooks() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val books = bookDao.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_SIZE)
                .allMatch(b -> b.getId() > 0)
                .allMatch(b -> !b.getTitle().isBlank())
                .allMatch(b -> b.getAuthors() != null && b.getAuthors().size() > 0 && b.getAuthors().get(0).getId() > 0)
                .allMatch(b -> b.getGenres() != null && b.getGenres().size() > 0 && b.getGenres().get(0).getId() > 0)
                .allMatch(b -> b.getComments() != null && b.getComments().size() > 0 && b.getComments().get(0).getId() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName("корректно обновлять книгу")
    @Test
    void updateBook() {
        val book = em.find(Book.class, EXPECTED_BOOK_ID);
        String oldTitle = book.getTitle();
        book.setTitle(BOOK_TITLE);

        val updateBook = em.find(Book.class, EXPECTED_BOOK_ID);

        assertThat(updateBook.getTitle()).isNotEqualTo(oldTitle).isEqualTo(BOOK_TITLE);
    }

    @DisplayName("корректно удалять книгу по id")
    @Test
    void deleteById() {
        val book = em.find(Book.class, EXPECTED_BOOK_ID);
        assertThat(book).isNotNull();
        em.detach(book);

        bookDao.deleteById(EXPECTED_BOOK_ID);
        val deletedBook = em.find(Book.class, EXPECTED_BOOK_ID);

        assertThat(deletedBook).isNull();
    }
}