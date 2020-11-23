package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dto.SimpleBook;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ShellApplication {

    private final CommentService commentService;
    private final BookService bookService;

    @ShellMethod(value = "Сохранение информации о книге", key = {"save book"})
    public Book createBook(String title, String genreName, String authorName, String comment) {
        return bookService.create(Book.builder()
                .title(title)
                .genres(List.of(new Genre(0, genreName)))
                .authors(List.of(new Author(0, authorName)))
                .comments(List.of(new Comment(0, comment)))
                .build());
    }

    @ShellMethod(value = "Получение книги по id", key = {"get book by id"})
    public SimpleBook getBookById(long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Получение всего списка книг", key = {"get books"})
    public List<SimpleBook> getAllBooks() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Обновление title книги по id", key = {"update book"})
    public SimpleBook updateBookById(long id, String title) {
        bookService.updateTitleById(id, title);
        return bookService.getById(id);
    }

    @ShellMethod(value = "Удаление книги по id", key = {"delete book"})
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Сохранение комментария", key = {"save comment"})
    public Comment createComment(String description) {
        return commentService.create(Comment.builder().description(description).build());
    }

    @ShellMethod(value = "Получение комментария по id", key = {"get comment by id"})
    public Comment getCommentById(long id) {
        return commentService.getById(id);
    }

    @ShellMethod(value = "Получение всех комментариев", key = {"get comments"})
    public List<Comment> getAllComments() {
        return commentService.getAll();
    }

    @ShellMethod(value = "Обновление description комментария по id", key = {"update comment"})
    public Comment updateCommentById(long id, String description) {
        commentService.updateDescriptionById(id, description);
        return commentService.getById(id);
    }

    @ShellMethod(value = "Удаление комментария по id", key = {"delete comment"})
    public void deleteCommentById(long id) {
        commentService.deleteById(id);
    }

    @ShellMethod(value = "Получение комментариев по id книги", key = {"get comment of book"})
    public List<String> getCommentsOfBook(long id) {
        return Optional.ofNullable(bookService.getById(id))
                .map(SimpleBook::getComments)
                .orElse(null);
    }
}
