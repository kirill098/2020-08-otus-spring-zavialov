package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString(exclude = "id")
@AllArgsConstructor
public class Book {

    private Long id;
    @NotBlank
    private final String title;
    @NotBlank
    private final String genreName;
    @NotBlank
    private final String authorName;
}
