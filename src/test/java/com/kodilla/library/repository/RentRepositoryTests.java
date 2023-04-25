package com.kodilla.library.repository;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.title.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RentRepositoryTests {

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private ReaderRepository readerRepository;

    private static final String BOOK_NAME = "GONE WITH THE WIND";
    private static final String AUTHOR = "Margaret Mitchell";
    private static final int PUBLICATION_YEAR = 1936;
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";

    @Test
    void testSaveRent() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
        titleRepository.save(title);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        copyRepository.save(copy);
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);
        readerRepository.save(reader);
        Rent rent = new Rent(copy, reader, new Date(), new Date());

        //When
        rentRepository.save(rent);

        //Then
        Optional<Rent> savedRent = rentRepository.findById(rent.getId());
        assertTrue(savedRent.isPresent());

        //CleanUp
        rentRepository.deleteById(rent.getId());
        readerRepository.deleteById(reader.getId());
        copyRepository.deleteById(copy.getId());
        titleRepository.deleteById(title.getId());
    }

    @Test
    void testDeleteRent() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
        titleRepository.save(title);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        copyRepository.save(copy);
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);
        readerRepository.save(reader);
        Rent rent = new Rent(copy, reader, new Date(), new Date());
        rentRepository.save(rent);

        //When
        rentRepository.deleteById(rent.getId());

        //Then
        assertFalse(rentRepository.existsById(rent.getId()));

        //CleanUp
        readerRepository.deleteById(reader.getId());
        copyRepository.deleteById(copy.getId());
        titleRepository.deleteById(title.getId());
    }

    @Test
    void testFindAll() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
        titleRepository.save(title);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        copyRepository.save(copy);
        Reader reader = new Reader(FIRST_NAME, LAST_NAME);
        readerRepository.save(reader);
        Rent rent1 = new Rent(copy, reader, new Date(), new Date());
        Rent rent2 = new Rent(copy, reader, new Date(), new Date());
        rentRepository.saveAll(Arrays.asList(rent1, rent2));

        //When
        List<Rent> rents = rentRepository.findAll();

        //Then
        assertEquals(2, rents.size());

        //CleanUp
        rents.forEach(rent -> rentRepository.deleteById(rent.getId()));
        readerRepository.deleteById(reader.getId());
        copyRepository.deleteById(copy.getId());
        titleRepository.deleteById(title.getId());
    }
}
