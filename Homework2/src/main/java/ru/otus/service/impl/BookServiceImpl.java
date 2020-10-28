package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.model.Book;
import ru.otus.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public Book create(String title, long genreId, long authorId) {
        long id = bookDao.insert(Book.builder()
                .title(title)
                .genreId(genreId)
                .authorId(authorId)
                .build());
        return bookDao.getById(id);
    }

    @Override
    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public Book updateBook(long id, String title, long genreId, long authorId) {
        bookDao.update(Book.builder()
                .id(id)
                .title(title)
                .genreId(genreId)
                .authorId(authorId)
                .build());
        return bookDao.getById(id);
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }
}
