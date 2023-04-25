package com.kodilla.library.repository;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.title.Title;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class CopyRepositoryTests {

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private RentRepository rentRepository;

    @Test
    void testSaveCopy() {
        //Given
        Title title = new Title("GONE WITH THE WIND", "Margaret Mitchell", 1936);
        titleRepository.save(title);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);

        //When
        copyRepository.save(copy);

        //Then
        Optional<Copy> savedCopy = copyRepository.findById(copy.getId());
        assertTrue(savedCopy.isPresent());
        assertEquals(CopyStatus.AVAILABLE, savedCopy.get().getStatus());

        //CleanUp
        copyRepository.deleteById(copy.getId());
        titleRepository.delete(title);
    }

    @Test
    void testDeleteCopy() {
        //Given
        Title title = new Title("GONE WITH THE WIND", "Margaret Mitchell", 1936);
        titleRepository.save(title);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        copyRepository.save(copy);

        //When
        copyRepository.deleteById(copy.getId());

        //Then
        assertFalse(copyRepository.existsById(copy.getId()));

        //CleanUp
        titleRepository.delete(title);
    }

    @Test
    void testFindAll() {
        //Given
        Title title = new Title("GONE WITH THE WIND", "Margaret Mitchell", 1936);
        titleRepository.save(title);
        Copy copy1 = new Copy(title, CopyStatus.AVAILABLE);
        Copy copy2 = new Copy(title, CopyStatus.CIRCULATION);
        copyRepository.saveAll(Arrays.asList(copy1, copy2));

        //When
        List<Copy> copies = copyRepository.findAll();

        //Then
        assertEquals(2, copies.size());

        //CleanUp
        copyRepository.deleteById(copy1.getId());
        copyRepository.deleteById(copy2.getId());
        titleRepository.delete(title);
    }

    @Test
    void testCopySave_RentsSaved() {
        //Given
        Title title = new Title("GONE WITH THE WIND", "Margaret Mitchell", 1936);
        titleRepository.save(title);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        Rent rent1 = new Rent();
        Rent rent2 = new Rent();
        copy.getRentsList().addAll(Arrays.asList(rent1, rent2));
        rent1.setCopy(copy);
        rent2.setCopy(copy);

        //When
        copyRepository.save(copy);

        //Then
        List<Rent> rents = rentRepository.findAll();
        assertEquals(2, rents.size());

        //CleanUp
        copyRepository.deleteById(copy.getId());
        titleRepository.delete(title);
    }

    @Test
    void testCopyDelete_RentsDeleted() {
        //Given
        Title title = new Title("GONE WITH THE WIND", "Margaret Mitchell", 1936);
        titleRepository.save(title);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        Rent rent1 = new Rent();
        Rent rent2 = new Rent();
        copy.getRentsList().addAll(Arrays.asList(rent1, rent2));
        rent1.setCopy(copy);
        rent2.setCopy(copy);
        copyRepository.save(copy);

        //When
        copyRepository.deleteById(copy.getId());

        //Then
        List<Rent> rents = rentRepository.findAll();
        assertEquals(0, rents.size());

        //CleanUp
        titleRepository.delete(title);
    }
}
