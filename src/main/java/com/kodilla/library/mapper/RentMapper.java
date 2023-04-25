package com.kodilla.library.mapper;

import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.rent.RentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<RentDto> mapToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }

    public List<Rent> mapToRentList(final List<RentDto> rentDtoList) {
        return rentDtoList.stream()
                .map(this::mapToRent)
                .collect(Collectors.toList());
    }
}
