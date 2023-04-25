package com.kodilla.library.repository;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.title.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TitleRepositoryTests {

    @Autowired
    private TitleRepository titleRepository;
    private static final String BOOK_NAME = "GONE WITH THE WIND";
    private static final String AUTHOR = "Margaret Mitchell";
    private static final int PUBLICATION_YEAR = 1936;

    @Test
    void testTitleSave() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);

        //When
        titleRepository.save(title);

        //Then
        long id = title.getId();
        Optional<Title> savedTitle = titleRepository.findById(id);
        assertTrue(savedTitle.isPresent());

        //CleanUp
        titleRepository.deleteById(id);
    }

    @Test
    void testTitleSaveWithCopies() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);

        Copy copy1 = new Copy(title, CopyStatus.AVAILABLE);
        Copy copy2 = new Copy(title, CopyStatus.LOST);
        Copy copy3 = new Copy(title, CopyStatus.CIRCULATION);
        title.getCopies().addAll(Arrays.asList(copy1, copy2, copy3));

        //When
        titleRepository.save(title);

        //Then
        long id = title.getId();
        assertNotEquals(0, id);

        //CleanUp
        titleRepository.deleteById(id);
    }
}
