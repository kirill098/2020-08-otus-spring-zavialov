package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.BookInfo;
import ru.otus.model.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GeneralService;
import ru.otus.service.GenreService;

@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Transactional
    @Override
    public BookInfo creteBookInfo(String title, String genreName, String authorName) {
        Genre genre = genreService.create(genreName);
        Author author = authorService.create(authorName);
        Book book = bookService.create(title, genre.getId(), author.getId());
        return BookInfo.builder()
                .title(book.getTitle())
                .genre(genre.getName())
                .author(author.getName())
                .build();
    }
}
