package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.BookDao;
import ru.otus.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genreId", book.getGenreId());
        params.addValue("authorId", book.getGenreId());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into books(title, genre_id, author_id) values(:title, :genreId, :authorId)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject("select * from books where id = :id", Map.of("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = Map.of("id", book.getId(),
                "title", book.getTitle(),
                "genreId", book.getGenreId(),
                "authorId", book.getAuthorId());
        jdbc.update("update books set title = :title, genre_id = :genreId, author_id = :authorId where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            Long genreId = resultSet.getLong("genre_id");
            Long authorId = resultSet.getLong("author_id");
            return new Book(id, title, genreId, authorId);
        }
    }
}