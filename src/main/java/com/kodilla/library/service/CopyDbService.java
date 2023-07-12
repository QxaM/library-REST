package com.kodilla.library.service;

import com.kodilla.library.controller.exception.CopyNotFoundException;
import com.kodilla.library.controller.exception.TitleNotFoundException;
import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.title.Title;
import com.kodilla.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CopyDbService {

    private final CopyRepository repository;
    private final TitleDbService titleService;

    public Copy createCopy(Copy copy) throws TitleNotFoundException {
        Title foundTitle = titleService.getTitle(copy.getTitle().getId());
        Copy copyToSave = new Copy(
                copy.getId(),
                foundTitle,
                copy.getStatus(),
                new ArrayList<>()
        );
        return repository.save(copyToSave);
    }

    public List<Copy> getCopies() {
        return repository.findAll();
    }

    public Copy getCopy(Long id) throws CopyNotFoundException {
        return repository.findById(id).orElseThrow(CopyNotFoundException::new);
    }

    public List<Copy> getAvailableCopies(Long titleId) throws TitleNotFoundException {
        Title foundTitle = titleService.getTitle(titleId);
        return repository.findCopiesByTitleAndStatus(foundTitle, CopyStatus.AVAILABLE);
    }

    public void deleteCopy(Long id) throws CopyNotFoundException {
        Copy copyToDelete = repository.findById(id).orElseThrow(CopyNotFoundException::new);
        repository.deleteById(copyToDelete.getId());
    }

    public Copy changeStatus(Long id, CopyStatus status) throws CopyNotFoundException {
        Copy copy = repository.findById(id).orElseThrow(CopyNotFoundException::new);
        copy.setStatus(status);
        return repository.save(copy);
    }
}
