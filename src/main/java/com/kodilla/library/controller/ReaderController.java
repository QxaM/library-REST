package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.ReaderNotFoundException;
import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.reader.ReaderDto;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderDbService service;
    private final ReaderMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> foundReaders = service.getAllReaders();
        return ResponseEntity.ok(mapper.mapToReaderDtoList(foundReaders));
    }

    @GetMapping(value = "{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable long readerId) throws ReaderNotFoundException {
        Reader foundReader = service.getReader(readerId);
        return ResponseEntity.ok(mapper.mapToReaderDto(foundReader));
    }

    @DeleteMapping(value = "{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable long readerId) throws ReaderNotFoundException {
        service.deleteReader(readerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) {
        Reader savedReader = service.saveReader(mapper.mapToReader(readerDto));
        return ResponseEntity.ok(mapper.mapToReaderDto(savedReader));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReaderDto> createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = mapper.mapToReader(readerDto);
        Reader createdReader = service.saveReader(reader);
        return ResponseEntity.ok(mapper.mapToReaderDto(createdReader));
    }
}
