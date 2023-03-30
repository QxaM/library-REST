package com.kodilla.library.controller;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyDto;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.title.Title;
import com.kodilla.library.mapper.CopyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/copy")
@RequiredArgsConstructor
public class CopyController {

    private final CopyMapper copyMapper;

    @GetMapping
    public CopyDto getCopy() {
        Title title = new Title("BOOK", "AUTHOR", 2000);
        return copyMapper.mapToCopyDto(new Copy(title, CopyStatus.AVAILABLE));
    }

    @GetMapping(value = "{copyId}")
    public CopyDto getCopy(@PathVariable long copyId) {
        Title title = new Title("BOOK" + copyId, "AUTHOR", 2000);
        return copyMapper.mapToCopyDto(new Copy(title, CopyStatus.AVAILABLE));
    }

    @DeleteMapping(value = "{copyId}")
    public void deleteCopy(@PathVariable long copyId) {

    }

    @PutMapping
    public CopyDto updateCopy() {
        Title title = new Title("CHANGED BOOK", "AUTHOR", 2000);
        return copyMapper.mapToCopyDto(new Copy(title, CopyStatus.AVAILABLE));
    }

    @PostMapping
    public void createCopy() {

    }
}
