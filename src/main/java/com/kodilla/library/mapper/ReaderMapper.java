package com.kodilla.library.mapper;

import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.reader.ReaderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderMapper {

    private final RentMapper rentMapper;

    public Reader mapToReader(final ReaderDto readerDTO) {
        return new Reader(
                readerDTO.getId(),
                readerDTO.getFirstName(),
                readerDTO.getLastName(),
                readerDTO.getCreatedDate(),
                rentMapper.mapToRentList(readerDTO.getRentList()));
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getCreatedDate(),
                rentMapper.mapToRentDtoList(reader.getRentsList()));
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readers) {
        return readers.stream()
                .map(this::mapToReaderDto)
                .toList();
    }
}
