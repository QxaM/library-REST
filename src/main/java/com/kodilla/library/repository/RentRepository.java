package com.kodilla.library.repository;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.rent.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentRepository extends CrudRepository<Rent, Long> {

    @Override
    List<Rent> findAll();
}
