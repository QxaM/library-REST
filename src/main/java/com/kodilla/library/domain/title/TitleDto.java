package com.kodilla.library.domain.title;

import com.kodilla.library.domain.copy.Copy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class TitleDto {

    private long id;
    private String title;
    private String author;
    private int publicationYear;
    private List<Copy> copies;
}
