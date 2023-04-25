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

    private final ReaderRepository repository;

    public List<Reader> getAllReaders() {
        return repository.findAll();
    }

    public Reader getReader(Long id) throws ReaderNotFoundException {
        return repository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public void deleteReader(Long id) throws ReaderNotFoundException {
        Reader foundReader = repository.findById(id).orElseThrow(ReaderNotFoundException::new);
        repository.delete(foundReader);
    }

    public Reader saveReader(Reader reader) {
        return repository.save(reader);
    }
}
