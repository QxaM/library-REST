package com.kodilla.library.mapper;

import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.rent.RentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentMapper {

    private final CopyMapper copyMapper;
    private final ReaderMapper readerMapper;

    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getId(),
                copyMapper.mapToCopy(rentDto.getCopy()),
                readerMapper.mapToReader(rentDto.getReader()),
                rentDto.getRentalDate(),
                rentDto.getReturnDate());
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                copyMapper.mapToCopyDto(rent.getCopy()),
                readerMapper.mapToReaderDto(rent.getReader()),
                rent.getRentalDate(),
                rent.getReturnDate());
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
