package com.kodilla.library.repository;

import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.rent.Rent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReaderRepositoryTests {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private RentRepository rentRepository;
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";

    @Test
    void testSaveReader() {
        //Given
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);

        //When
        readerRepository.save(reader);

        //Then
        Optional<Reader> readReader = readerRepository.findById(reader.getId());
        assertTrue(readReader.isPresent());

        //CleanUp
        readerRepository.deleteById(reader.getId());
    }

    @Test
    void testDeleteReader() {
        //Given
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);
        readerRepository.save(reader);

        //When
        readerRepository.deleteById(reader.getId());

        //Then
        assertFalse(readerRepository.existsById(reader.getId()));
    }

    @Test
    void testFindAll() {
        //Given
        Reader reader1 = new Reader(FIRST_NAME, LAST_NAME);
        Reader reader2 = new Reader(FIRST_NAME, LAST_NAME);
        readerRepository.saveAll(Arrays.asList(reader1, reader2));

        //When
        List<Reader> readers = readerRepository.findAll();

        //Then
        assertEquals(2, readers.size());

        //CleanUp
        readers.forEach(reader -> readerRepository.deleteById(reader.getId()));
    }

    @Test
    void testReaderSave_RentSaved() {
        //Given
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);
        Rent rent1 = new Rent();
        Rent rent2 = new Rent();
        rent1.setReader(reader);
        rent2.setReader(reader);
        reader.getRentsList().add(rent1);
        reader.getRentsList().add(rent2);

        //When
        readerRepository.save(reader);

        //Then
        List<Rent> rents = rentRepository.findAll();
        assertEquals(2, rents.size());

        //cleanUp
        readerRepository.deleteById(reader.getId());
    }

    @Test
    void testReaderDelete_RentDeleted() {
        //Given
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);
        Rent rent1 = new Rent();
        Rent rent2 = new Rent();
        rent1.setReader(reader);
        rent2.setReader(reader);
        reader.getRentsList().add(rent1);
        reader.getRentsList().add(rent2);
        readerRepository.save(reader);

        //When
        readerRepository.deleteById(reader.getId());

        //Then
        List<Rent> rents = rentRepository.findAll();
        assertEquals(0, rents.size());
    }
}
