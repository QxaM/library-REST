package com.kodilla.library.service;

import com.kodilla.library.controller.exception.TitleNotFoundException;
import com.kodilla.library.domain.title.Title;
import com.kodilla.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleDbService {

    private final TitleRepository repository;

    public Title createTitle(Title title) {
        return repository.save(title);
    }

    public List<Title> getAllTitles() {
        return repository.findAll();
    }

    public Title getTitle(Long titleId) throws TitleNotFoundException {
        return repository.findById(titleId).orElseThrow(TitleNotFoundException::new);
    }

    public void deleteTitle(Long titleId) throws TitleNotFoundException {
        Title title = repository.findById(titleId).orElseThrow(TitleNotFoundException::new);
        repository.delete(title);
    }
}
