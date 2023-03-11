package com.kodilla.library.service;

import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
}
