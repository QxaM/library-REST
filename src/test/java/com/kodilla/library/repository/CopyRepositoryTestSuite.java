package com.kodilla.library.repository;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.title.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CopyRepositoryTestSuite {

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private CopyRepository copyRepository;
    private static final Title TITLE = new Title("GONE WITH THE WIND", "Margaret Mitchell", 1936);

    @Test
    void testCopySave() {
        //Given
        titleRepository.save(TITLE);
        Copy copy = new Copy(TITLE, CopyStatus.AVAILABLE);

        //When
        copyRepository.save(copy);

        //Then
        long id = copy.getId();
        Optional<Copy> savedCopy = copyRepository.findById(id);
        assertTrue(savedCopy.isPresent());

        //CleanUp
        copyRepository.deleteById(id);

        long titleId = TITLE.getId();
        titleRepository.deleteById(titleId);

    }
}
