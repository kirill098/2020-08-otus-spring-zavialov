package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Cell implements Serializable {

    private String question;
    private String expectedAnswer;
    private String actualAnswer;

}
