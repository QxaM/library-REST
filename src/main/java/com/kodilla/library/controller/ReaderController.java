package com.kodilla.library.controller;

import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.reader.ReaderDto;
import com.kodilla.library.mapper.ReaderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("library/v1/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderMapper readerMapper;

    @GetMapping
    public ReaderDto getReader() {
        return readerMapper.mapToReaderDto(new Reader("Jan", "Kowalski"));
    }

    @GetMapping(value = "{readerId}")
    public ReaderDto getReader(@PathVariable long readerId) {
        return readerMapper.mapToReaderDto(new Reader("Jan", "Kowalski" + readerId));
    }

    @DeleteMapping(value = "{readerId}")
    public void deleteReader(@PathVariable long readerId) {

    }

    @PutMapping
    public ReaderDto updateReader() {
        return readerMapper.mapToReaderDto(new Reader("Jan", "Changed"));
    }

    @PostMapping
    public void createReader() {

    }
}
