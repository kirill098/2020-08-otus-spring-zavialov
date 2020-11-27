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
public class BookDescription {

    private String title;
    private List<String> genres;
    private List<String> authors;
}
