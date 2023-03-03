package com.kodilla.library.repository;

import com.kodilla.library.domain.copy.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CopyRepository extends CrudRepository<Copy, Long> {
}
