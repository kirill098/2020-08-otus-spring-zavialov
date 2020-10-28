package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Book {

    private long id;
    private final String title;
    private final Long genreId;
    private final Long authorId;
}
