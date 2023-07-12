package com.kodilla.library.service;

import com.kodilla.library.controller.exception.CopyNotFoundException;
import com.kodilla.library.controller.exception.CopyNotInCirculationException;
import com.kodilla.library.controller.exception.ReaderNotFoundException;
import com.kodilla.library.controller.exception.RentNotFoundException;
import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyStatus;
import com.kodilla.library.domain.reader.Reader;
import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentDbService {

    private final RentRepository repository;
    private final CopyDbService copyService;
    private final ReaderDbService readerService;

    @Transactional
    public Rent createRent(Long copyId, Long readerId) throws CopyNotFoundException,
                                                                ReaderNotFoundException {
        Copy copyToRent = copyService.getCopy(copyId);
        Reader readerRenting = readerService.getReader(readerId);
        Rent rent = new Rent(copyToRent, readerRenting, new Date());

        copyService.changeStatus(copyToRent.getId(), CopyStatus.CIRCULATION);
        return repository.save(rent);
    }

    @Transactional
    public Rent returnCopy(Rent rent) throws RentNotFoundException,
                                            CopyNotFoundException,
                                            CopyNotInCirculationException {
        Rent foundRent = repository.findById(rent.getId()).orElseThrow(RentNotFoundException::new);
        if(foundRent.getReturnDate() != null) {
            throw new CopyNotInCirculationException();
        }
        foundRent.setReturnDate(new Date());

        copyService.changeStatus(foundRent.getCopy().getId(), CopyStatus.AVAILABLE);
        return repository.save(foundRent);
    }

    public List<Rent> getRents() {
        return repository.findAll();
    }

    public Rent getRent(Long id) throws  RentNotFoundException {
        return repository.findById(id)
                .orElseThrow(RentNotFoundException::new);
    }
}
