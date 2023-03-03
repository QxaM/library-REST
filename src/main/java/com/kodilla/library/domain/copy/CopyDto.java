package com.kodilla.library.domain.copy;

import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.title.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class CopyDto {

    private long id;
    private Title title;
    private CopyStatus status;
    private List<Rent> rentsList;
}
