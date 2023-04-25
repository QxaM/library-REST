package com.kodilla.library.domain.reader;

import com.kodilla.library.domain.rent.RentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date createdDate;
    private List<RentDto> rentList;
}
