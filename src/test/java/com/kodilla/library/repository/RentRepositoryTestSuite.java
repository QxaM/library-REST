package com.kodilla.library.repository;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.title.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RentRepositoryTestSuite {

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
    void testRentSave() {
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
        Long rentId = rent.getId();
        Optional<Rent> savedRent = rentRepository.findById(rentId);
        assertTrue(savedRent.isPresent());

        //CleanUp
        rentRepository.deleteById(rentId);

        Long readerId = reader.getId();
        readerRepository.deleteById(readerId);

        Long copyId = copy.getId();
        copyRepository.deleteById(copyId);

        Long titleId = title.getId();
        titleRepository.deleteById(titleId);
    }
}
