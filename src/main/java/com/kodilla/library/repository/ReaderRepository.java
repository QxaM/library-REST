package com.kodilla.library.repository;

import com.kodilla.library.domain.reader.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    @Override
    List<Reader> findAll();
}
