package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.otus.dao.BookDao;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.model.dao.BookDb;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Validated
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public Book create(@Valid Book book) {
        Genre genre = genreService.ensure(Genre.builder().name(book.getGenreName()).build());
        Author author = authorService.ensure(Author.builder().name(book.getAuthorName()).build());

        long id = bookDao.insert(BookDb.builder()
                .title(book.getTitle())
                .genreId(genre.getId())
                .authorId(author.getId())
                .build());

        BookDb result = bookDao.getById(id);

        return Book.builder()
                .id(result.getId())
                .title(result.getTitle())
                .genreName(genre.getName())
                .authorName(author.getName())
                .build();
    }

    @Override
    public Book getBookById(long id) {
        BookDb bookDb = bookDao.getById(id);

        Genre genre = genreService.getById(bookDb.getGenreId());
        Author author = authorService.getById(bookDb.getAuthorId());

        return Book.builder()
                .title(bookDb.getTitle())
                .genreName(genre.getName())
                .authorName(author.getName())
                .build();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll().stream()
                .map(b -> {
                    Genre genre = genreService.getById(b.getGenreId());
                    Author author = authorService.getById(b.getAuthorId());
                    return Book.builder()
                            .title(b.getTitle())
                            .genreName(genre.getName())
                            .authorName(author.getName())
                            .build();
                })
                .collect(toList());
    }

    @Override
    public Book updateBook(@Valid Book book) {
        Genre genre = genreService.getByName(book.getGenreName());
        Author author = authorService.getByName(book.getAuthorName());

        bookDao.update(BookDb.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genreId(genre.getId())
                .authorId(author.getId())
                .build());

        return Book.builder()
                .id(book.getId())
                .genreName(genre.getName())
                .authorName(author.getName())
                .build();
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }
}
