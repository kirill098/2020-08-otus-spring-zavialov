package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.dto.SimpleBook;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;
import ru.otus.service.BookService;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public Book create(Book book) {
        return bookDao.save(book);
    }

    @Override
    public SimpleBook getById(long id) {
        return bookDao.findById(id).map(convertToSimpleBook).orElse(null);
    }

    @Override
    public List<SimpleBook> getAll() {
        return bookDao.findAll().stream().map(convertToSimpleBook).collect(toList());
    }

    @Override
    public void updateTitleById(long id, String title) {
        bookDao.findById(id).ifPresent(val -> val.setTitle(title));
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    private static Function<Book, SimpleBook> convertToSimpleBook = (book) -> SimpleBook.builder()
            .title(book.getTitle())
            .authors(book.getAuthors().stream().map(Author::getName).collect(toList()))
            .genres(book.getGenres().stream().map(Genre::getName).collect(toList()))
            .comments(book.getComments().stream().map(Comment::getDescription).collect(toList()))
            .build();
}
