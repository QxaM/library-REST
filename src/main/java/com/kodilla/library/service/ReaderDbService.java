package com.kodilla.library.service;

import com.kodilla.library.controller.exception.ReaderNotFoundException;
import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderDbService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(Long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public void deleteReader(Long id) throws ReaderNotFoundException {
        readerRepository.deleteById(id);
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }
}
