package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellApplication {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @ShellMethod(value = "Сохранение информации о книге", key = {"s", "save book"})
    public Book createBook(String title, String genreName, String authorName) {
        return bookService.create(Book.builder()
                .title(title)
                .genreName(genreName)
                .authorName(authorName)
                .build());
    }

    @ShellMethod(value = "Получение книги по id", key = {"get by id", "get book by id"})
    public Book getBookById(long id) {
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "Получение всего списка книг", key = {"gb", "get books", "get all books"})
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Обновление книги по id", key = {"u", "update"})
    public Book updateBook(long id, String title, String genreName, String authorName) {
        return bookService.updateBook(Book.builder()
                .id(id)
                .title(title)
                .genreName(genreName)
                .authorName("")
                .build());
    }

    @ShellMethod(value = "Удаление книги по id", key = {"d", "delete"})
    public void deleteBook(long id) {
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "Получение всего списка жанров", key = {"gg", "get genres", "get all genres"})
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }

    @ShellMethod(value = "Получение всего списка авторов", key = {"ga", "get authors", "get all authors"})
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }
}
