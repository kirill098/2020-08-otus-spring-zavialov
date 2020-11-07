package ru.otus.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookDb {

    private long id;
    private final String title;
    private final Long genreId;
    private final Long authorId;
}