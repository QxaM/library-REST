package com.kodilla.library.controller;

import com.kodilla.library.domain.title.Title;
import com.kodilla.library.domain.title.TitleDto;
import com.kodilla.library.mapper.TitleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("library/v1/title")
public class TitleController {

    private final TitleMapper titleMapper;

    @GetMapping
    public TitleDto getTitle(){
        return titleMapper.mapToTitleDto(new Title("BOOK", "AUTHOR", 2000));
    }

    @GetMapping(value = "{titleId}")
    public TitleDto getTitle(@PathVariable long titleId) {
        return titleMapper.mapToTitleDto(new Title("BOOK" + titleId, "AUTHOR", 2000));
    }

    @DeleteMapping(value = "{titleId}")
    public void deleteTitle(@PathVariable Long titleId) {

    }

    @PutMapping
    public TitleDto updateTitle() {
        return titleMapper.mapToTitleDto(new Title("CHANGED BOOK", "AUTHOR", 2000));
    }

    @PostMapping
    public void createTitle() {

    }
}
