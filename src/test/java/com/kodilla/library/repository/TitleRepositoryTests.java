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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TitleRepositoryTests {

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private CopyRepository copyRepository;
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
        assertEquals(BOOK_NAME, savedTitle.get().getTitle());

        //CleanUp
        titleRepository.deleteById(id);
    }

    @Test
    void testTitleDelete() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
        titleRepository.save(title);

        //When
        titleRepository.deleteById(title.getId());

        //Then
        assertFalse(titleRepository.existsById(title.getId()));

        //ClenUp
    }

    @Test
    void testFindAll() {
        //Given
        Title title1 = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
        Title title2 = new Title("Book 2", "Author 2", 2000);
        titleRepository.save(title1);
        titleRepository.save(title2);

        //When
        List<Title> titleList = titleRepository.findAll();

        //Then
        assertEquals(2, titleList.size());

        //cleanUp
        titleRepository.deleteById(title1.getId());
        titleRepository.deleteById(title2.getId());
    }

    @Test
    void testTitleSaveWithCopies_CopiesPersists() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);

        Copy copy1 = new Copy(title, CopyStatus.AVAILABLE);
        Copy copy2 = new Copy(title, CopyStatus.LOST);
        Copy copy3 = new Copy(title, CopyStatus.CIRCULATION);
        title.getCopies().addAll(Arrays.asList(copy1, copy2, copy3));

        //When
        titleRepository.save(title);

        //Then
        List<Copy> copies = copyRepository.findAll();
        assertEquals(3, copies.size());

        //CleanUp
        copies.forEach(copy -> copyRepository.deleteById(copy.getId()));
        titleRepository.deleteById(title.getId());
    }

    @Test
    void testTitleDeleteWithCopies_CopiesDeleted() {
        //Given
        Title title = new Title(BOOK_NAME, AUTHOR, PUBLICATION_YEAR);

        Copy copy1 = new Copy(title, CopyStatus.AVAILABLE);
        Copy copy2 = new Copy(title, CopyStatus.LOST);
        Copy copy3 = new Copy(title, CopyStatus.CIRCULATION);
        title.getCopies().addAll(Arrays.asList(copy1, copy2, copy3));

        titleRepository.save(title);

        //When
        titleRepository.deleteById(title.getId());

        //Then
        assertFalse(titleRepository.existsById(title.getId()));
        assertFalse(copyRepository.existsById(copy1.getId()));
        assertFalse(copyRepository.existsById(copy2.getId()));
        assertFalse(copyRepository.existsById(copy3.getId()));
        assertEquals(0, copyRepository.findAll().size());
    }
}
