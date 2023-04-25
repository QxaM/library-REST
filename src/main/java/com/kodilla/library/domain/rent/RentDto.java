package com.kodilla.library.domain.rent;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class RentDto {
    private long id;
    private Copy copy;
    private Reader reader;
    private Date rentalDate;
    private Date returnDate;
}
