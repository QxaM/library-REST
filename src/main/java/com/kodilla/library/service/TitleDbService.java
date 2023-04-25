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

    private final TitleRepository titleRepository;

    public Title createTitle(Title title) {
        return titleRepository.save(title);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitle(Long titleId) throws TitleNotFoundException {
        return titleRepository.findById(titleId).orElseThrow(TitleNotFoundException::new);
    }

    public void deleteTitle(Long titleId) throws TitleNotFoundException {
        Title title = titleRepository.findById(titleId).orElseThrow(TitleNotFoundException::new);
        titleRepository.delete(title);
    }
}
