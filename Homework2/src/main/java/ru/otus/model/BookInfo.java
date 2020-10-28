package ru.otus.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BookInfo {

    private final String title;
    private final String genre;
    private final String author;
}
