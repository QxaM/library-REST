package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.CopyNotFoundException;
import com.kodilla.library.controller.exception.CopyNotInCirculationException;
import com.kodilla.library.controller.exception.ReaderNotFoundException;
import com.kodilla.library.controller.exception.RentNotFoundException;
import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.rent.RentDto;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentMapper mapper;
    private final RentDbService service;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents() {
        return ResponseEntity.ok(mapper.mapToRentDtoList(service.getRents()));
    }

    @GetMapping(value = "{rentId}")
    public RentDto getRent(@PathVariable Long rentId) throws RentNotFoundException {
        return mapper.mapToRentDto(service.getRent(rentId));
    }

    @PutMapping(value = "returnRental")
    public ResponseEntity<RentDto> returnRental(@RequestBody RentDto rentDto) throws RentNotFoundException,
                                                                                    CopyNotFoundException,
                                                                                    CopyNotInCirculationException {
        Rent rentToReturn = mapper.mapToRent(rentDto);
        return ResponseEntity.ok(mapper.mapToRentDto(service.returnCopy(rentToReturn)));
    }

    @PostMapping(value = "rentCopy/{copyId}/byReader/{readerId}")
    public ResponseEntity<RentDto> createRent(@PathVariable Long copyId,
                                     @PathVariable Long readerId) throws CopyNotFoundException,
                                                                ReaderNotFoundException {
        Rent rent = service.createRent(copyId, readerId);
        return ResponseEntity.ok(mapper.mapToRentDto(rent));
    }
}
