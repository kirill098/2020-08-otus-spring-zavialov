package ru.otus.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cell implements Serializable {

    private String question;
    private String expectedAnswer;
    private String actualAnswer;

}
