package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString(includeFieldNames = false)
@AllArgsConstructor
public class SimpleBook {

    private final String title;
    private final List<String> genres;
    private final List<String> authors;
    private final List<String> comments;
}
