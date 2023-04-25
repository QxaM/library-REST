package com.kodilla.library.domain.rent;

import com.kodilla.library.domain.copy.CopyDto;
import com.kodilla.library.domain.reader.ReaderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Long id;
    private CopyDto copy;
    private ReaderDto reader;
    private Date rentalDate;
    private Date returnDate;
}
