package com.kodilla.library.domain.reader;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodilla.library.domain.rent.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReaderDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Date createdDate = new Date();
    private List<Rent> rentList;
}
