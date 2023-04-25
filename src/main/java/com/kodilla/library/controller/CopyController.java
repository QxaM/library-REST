package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.CopyNotFoundException;
import com.kodilla.library.controller.exception.TitleNotFoundException;
import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyDto;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.mapper.CopyMapper;
import com.kodilla.library.service.CopyDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/copy")
@RequiredArgsConstructor
public class CopyController {

    private final CopyMapper mapper;
    private final CopyDbService service;

    @GetMapping
    public ResponseEntity<List<CopyDto>> getCopies() {
        return ResponseEntity.ok(mapper.mapToCopyDtoList(service.getCopies()));
    }

    @GetMapping(value = "{copyId}")
    public ResponseEntity<CopyDto> getCopy(@PathVariable Long copyId) throws CopyNotFoundException {
        return ResponseEntity.ok(mapper.mapToCopyDto(service.getCopy(copyId)));
    }

    @GetMapping(value = "findAvailable/{titleId}")
    public ResponseEntity<List<CopyDto>> getAvailableCopies(@PathVariable Long titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(mapper.mapToCopyDtoList(service.getAvailableCopies(titleId)));
    }

    @DeleteMapping(value = "{copyId}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long copyId) throws CopyNotFoundException {
        service.deleteCopy(copyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{copyId}/changeStatus/{status}")
    public ResponseEntity<CopyDto> changeCopyStatus(@PathVariable Long copyId,
                                                    @PathVariable CopyStatus status) throws CopyNotFoundException{
        return ResponseEntity.ok(mapper.mapToCopyDto(service.changeStatus(copyId, status)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CopyDto> createCopy(@RequestBody CopyDto copyDto) throws TitleNotFoundException {
        Copy copy = mapper.mapToCopy(copyDto);
        return ResponseEntity.ok(mapper.mapToCopyDto(service.createCopy(copy)));
    }
}
