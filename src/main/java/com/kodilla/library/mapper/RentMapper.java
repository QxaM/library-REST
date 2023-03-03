package com.kodilla.library.mapper;

import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.rent.RentDto;
import org.springframework.stereotype.Service;

@Service
public class RentMapper {

    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getId(),
                rentDto.getCopy(),
                rentDto.getReader(),
                rentDto.getRentalDate(),
                rentDto.getReturnDate());
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getCopy(),
                rent.getReader(),
                rent.getRentalDate(),
                rent.getReturnDate());
    }
}
