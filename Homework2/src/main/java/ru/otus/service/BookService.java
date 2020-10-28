package ru.otus.service;

import ru.otus.model.Book;

import java.util.List;

public interface BookService {

    Book create(String title, long genreId, long authorId);
    Book getBookById(long id);
    List<Book> getAllBooks();
    Book updateBook(long id, String title, long genreId, long authorId);
    void deleteBookById(long id);
}
