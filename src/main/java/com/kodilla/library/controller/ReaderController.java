package com.kodilla.library.controller;

import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.reader.ReaderDto;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.DbService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.MemberSubstitution;
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
