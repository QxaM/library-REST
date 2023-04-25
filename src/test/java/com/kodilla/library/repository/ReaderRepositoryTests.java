package com.kodilla.library.repository;

import com.kodilla.library.domain.reader.Reader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReaderRepositoryTests {

    @Autowired
    private ReaderRepository readerRepository;
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";

    @Test
    void testReaderSave() {
        //Given
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);

        //When
        readerRepository.save(reader);

        //Then
        Long id = reader.getId();
        Optional<Reader> readReader = readerRepository.findById(id);
        assertTrue(readReader.isPresent());

        //CleanUp
        readerRepository.deleteById(id);
    }
}
