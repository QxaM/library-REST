package com.kodilla.library.controller;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.rent.RentDto;
import com.kodilla.library.domain.title.Title;
import com.kodilla.library.mapper.RentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "library/v1/rent")
@RequiredArgsConstructor
public class RentController {

    private final RentMapper rentMapper;

    @GetMapping
    public RentDto getRent() {
        Title title = new Title("BOOK", "AUTHOR", 2000);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        Reader reader = new Reader("Jan", "Kowalski");
        return rentMapper.mapToRentDto(new Rent(copy, reader, new Date(), new Date()));
    }

    @GetMapping(value = "{rentId}")
    public RentDto getRent(@PathVariable long rentId) {
        Title title = new Title("BOOK" + rentId, "AUTHOR", 2000);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        Reader reader = new Reader("Jan", "Kowalski");
        return rentMapper.mapToRentDto(new Rent(copy, reader, new Date(), new Date()));
    }

    @DeleteMapping(value = "{rentId}")
    public void deleteRent(@PathVariable long rentId) {

    }

    @PutMapping()
    public RentDto updateRent() {
        Title title = new Title("CHANGE BOOK", "AUTHOR", 2000);
        Copy copy = new Copy(title, CopyStatus.AVAILABLE);
        Reader reader = new Reader("Jan", "Kowalski");
        return rentMapper.mapToRentDto(new Rent(copy, reader, new Date(), new Date()));
    }

    @PostMapping
    public void createRent() {

    }
}
