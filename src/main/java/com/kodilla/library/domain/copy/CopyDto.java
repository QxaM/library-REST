package com.kodilla.library.domain.copy;

import com.kodilla.library.domain.title.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CopyDto {

    private long id;
    private Title title;
    private CopyStatus status;
}
