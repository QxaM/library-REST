package com.kodilla.library.domain.reader;

import com.kodilla.library.domain.rent.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class ReaderDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Date createdDate;
    private List<Rent> rentList;
}
