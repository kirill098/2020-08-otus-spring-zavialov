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
public class Genre {

    private long id;
    @NotBlank
    private final String name;
}
