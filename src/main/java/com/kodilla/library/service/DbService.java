package com.kodilla.library.service;

import com.kodilla.library.controller.ElementNotFoundException;
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

    public Reader getReader(Long id) throws ElementNotFoundException {
        return readerRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public void deleteReader(Long id) throws ElementNotFoundException {
        readerRepository.deleteById(id);
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }
}
