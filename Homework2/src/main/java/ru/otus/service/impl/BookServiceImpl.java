package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.dto.BookDescription;
import ru.otus.dto.SimpleBook;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.service.BookService;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Transactional
    @Override
    public Book create(Book book) {
        return bookDao.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public SimpleBook getById(long id) {
        return bookDao.findById(id).map(convertToSimpleBook).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDescription> getAll() {
        return ((List<Book>)bookDao.findAll()).stream().map(convertToBookDescription).collect(toList());
    }

    @Transactional
    @Override
    public void updateTitleById(long id, String title) {
        bookDao.findById(id).ifPresent(val -> val.setTitle(title));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    private static Function<Book, SimpleBook> convertToSimpleBook = (book) -> SimpleBook.builder()
            .title(book.getTitle())
            .authors(book.getAuthors().stream().map(Author::getName).collect(toList()))
            .genres(book.getGenres().stream().map(Genre::getName).collect(toList()))
            .comments(null)
            .build();

    private static Function<Book, BookDescription> convertToBookDescription = (book) -> BookDescription.builder()
            .title(book.getTitle())
            .authors(book.getAuthors().stream().map(Author::getName).collect(toList()))
            .genres(book.getGenres().stream().map(Genre::getName).collect(toList()))
            .build();
}
