package com.kodilla.library.mapper;

import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.reader.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDTO) {
        return new Reader(
                readerDTO.getId(),
                readerDTO.getFirstName(),
                readerDTO.getLastName(),
                readerDTO.getCreatedDate(),
                new ArrayList<>());
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getCreatedDate());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readers) {
        return readers.stream()
                .map(this::mapToReaderDto)
                .toList();
    }
}
