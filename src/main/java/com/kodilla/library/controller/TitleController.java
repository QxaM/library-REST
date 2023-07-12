package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.TitleNotFoundException;
import com.kodilla.library.domain.title.Title;
import com.kodilla.library.domain.title.TitleDto;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO porozdzielać service z linii

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/titles")
public class TitleController {

    private final TitleMapper mapper;
    private final TitleDbService service;

    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitle(){
        return ResponseEntity.ok(mapper.mapToTitleDtoList(service.getAllTitles()));
    }

    @GetMapping(value = "{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable Long titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(mapper.mapToTitleDto(service.getTitle(titleId)));
    }

    @DeleteMapping(value = "{titleId}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long titleId) throws TitleNotFoundException {
        service.deleteTitle(titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TitleDto> updateTitle(@RequestBody TitleDto titleDto) {
        Title titleToUpdate = mapper.mapToTitle(titleDto);
        return ResponseEntity.ok(mapper.mapToTitleDto(service.createTitle(titleToUpdate)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TitleDto> createTitle(@RequestBody TitleDto titleDto) {
        Title titleToSave = mapper.mapToTitle(titleDto);
        return ResponseEntity.ok(mapper.mapToTitleDto(service.createTitle(titleToSave)));
    }
}
