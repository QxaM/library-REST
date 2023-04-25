package com.kodilla.library.domain.title;

import com.kodilla.library.domain.copy.Copy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
}
