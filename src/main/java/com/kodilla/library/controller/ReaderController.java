package com.kodilla.library.controller;

import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.reader.ReaderDto;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.DbService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.MemberSubstitution;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/v1/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final DbService service;
    private final ReaderMapper readerMapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReader() {
        List<Reader> readers = service.getAllReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(readers));
    }

    @GetMapping(value = "{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable long readerId) throws ElementNotFoundException {
        return ResponseEntity.ok(readerMapper.mapToReaderDto(service.getReader(readerId)));
    }

    @DeleteMapping(value = "{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable long readerId) throws ElementNotFoundException {
        service.deleteReader(readerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader savedReader = service.saveReader(reader);
        return ResponseEntity.ok(readerMapper.mapToReaderDto(savedReader));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {

        Reader reader = readerMapper.mapToReader(readerDto);
        service.saveReader(reader);
        return ResponseEntity.ok().build();
    }
}
