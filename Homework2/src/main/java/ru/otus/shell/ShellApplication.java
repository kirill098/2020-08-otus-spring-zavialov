package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.BookDao;
import ru.otus.dao.CommentDao;
import ru.otus.dto.SimpleBook;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@ShellComponent
@RequiredArgsConstructor
public class ShellApplication {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @ShellMethod(value = "Сохранение информации о книге", key = {"save book"})
    public SimpleBook createBook(String title, String genreName, String authorName, String comment) {
        return Optional.of(bookDao.save(Book.builder()
                .title(title)
                .genres(List.of(new Genre(0, genreName)))
                .authors(List.of(new Author(0, authorName)))
                .comments(List.of(new Comment(0, comment)))
                .build()))
                .map(convertToSimpleBook)
                .orElseThrow(() -> new IllegalStateException("Book was not saved"));
    }

    @ShellMethod(value = "Получение книги по id", key = {"get book by id"})
    public SimpleBook getBookById(long id) {
        return bookDao.findById(id).map(convertToSimpleBook).orElse(null);
    }

    @ShellMethod(value = "Получение книг по title", key = {"get book by title"})
    public SimpleBook getBookByTitle(String title) {
        return bookDao.findByTitle(title).map(convertToSimpleBook).orElse(null);
    }

    @ShellMethod(value = "Получение всего списка книг", key = {"get books"})
    public List<SimpleBook> getAllBooks() {
        return bookDao.findAll().stream().map(convertToSimpleBook).collect(toList());
    }

    @ShellMethod(value = "Обновление title книги по id", key = {"update book"})
    public void updateBookById(long id, String title) {
        bookDao.updateTitleById(id, title);
    }

    @ShellMethod(value = "Удаление книги по id", key = {"delete book"})
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

    @ShellMethod(value = "Сохранение комментария", key = {"save comment"})
    public Comment createComment(String description) {
        return commentDao.save(Comment.builder().description(description).build());
    }

    @ShellMethod(value = "Получение комментария по id", key = {"get comment by id"})
    public Comment getCommentById(long id) {
        return commentDao.findById(id).orElse(null);
    }

    @ShellMethod(value = "Получение комментария по description", key = {"get comment by description"})
    public Comment getCommentByDescription(String description) {
        return commentDao.findByDescription(description).orElse(null);
    }

    @ShellMethod(value = "Получение всех комментариев", key = {"get comments"})
    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }

    @ShellMethod(value = "Обновление description комментария по id", key = {"update comment"})
    public void updateCommentById(long id, String description) {
        commentDao.updateDescriptionById(id, description);
    }

    @ShellMethod(value = "Удаление комментария по id", key = {"delete comment"})
    public void deleteCommentById(long id) {
        commentDao.deleteById(id);
    }

    private static Function<Book, SimpleBook> convertToSimpleBook = (book) -> SimpleBook.builder()
            .title(book.getTitle())
            .authors(book.getAuthors().stream().map(Author::getName).collect(toList()))
            .genres(book.getGenres().stream().map(Genre::getName).collect(toList()))
            .comments(book.getComments().stream().map(Comment::getDescription).collect(toList()))
            .build();
}
