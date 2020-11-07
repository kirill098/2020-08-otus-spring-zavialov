package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.GenreDao;
import ru.otus.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into genres(name) values (:name)", params ,kh);
        return kh.getKey().longValue();
    }

    @Override
    public Genre getById(long id) {
        return jdbc.query("select id, name from genres where id = :id", Map.of("id", id), new GenreMapper()).stream()
                .reduce((a, b) -> {
                    throw new IllegalStateException(String.format("Too many genres were found by id=%s", id));
                }).orElse(null);
    }

    @Override
    public Genre getByName(String name) {
        return jdbc.query("select id, name from genres where name = :name", Map.of("name", name), new GenreMapper()).stream()
                .reduce((a, b) -> {
                    throw new IllegalStateException(String.format("Too many genres were found by name=%s", name));
                }).orElse(null);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
