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

import java.util.List;

@RestController
@RequestMapping(value = "/v1/copies")
@RequiredArgsConstructor
public class CopyController {

    private final CopyMapper mapper;
    private final CopyDbService service;

    @GetMapping
    public ResponseEntity<List<CopyDto>> getCopies() {
        List<Copy> foundCopies = service.getCopies();
        return ResponseEntity.ok(mapper.mapToCopyDtoList(foundCopies));
    }

    @GetMapping(value = "{copyId}")
    public ResponseEntity<CopyDto> getCopy(@PathVariable Long copyId) throws CopyNotFoundException {
        Copy foundCopy = service.getCopy(copyId);
        return ResponseEntity.ok(mapper.mapToCopyDto(foundCopy));
    }

    @GetMapping(value = "findAvailable/{titleId}")
    public ResponseEntity<List<CopyDto>> getAvailableCopies(@PathVariable Long titleId) throws TitleNotFoundException {
        List<Copy> foundCopies = service.getAvailableCopies(titleId);
        return ResponseEntity.ok(mapper.mapToCopyDtoList(foundCopies));
    }

    @DeleteMapping(value = "{copyId}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long copyId) throws CopyNotFoundException {
        service.deleteCopy(copyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{copyId}/changeStatus/{status}")
    public ResponseEntity<CopyDto> changeCopyStatus(@PathVariable Long copyId,
                                                    @PathVariable CopyStatus status) throws CopyNotFoundException{
        Copy updatedCopy = service.changeStatus(copyId, status);
        return ResponseEntity.ok(mapper.mapToCopyDto(updatedCopy));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CopyDto> createCopy(@RequestBody CopyDto copyDto) throws TitleNotFoundException {
        Copy copy = mapper.mapToCopy(copyDto);
        Copy createdCopy = service.createCopy(copy);
        return ResponseEntity.ok(mapper.mapToCopyDto(createdCopy));
    }
}
