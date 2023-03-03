package com.kodilla.library.repository;

import com.kodilla.library.domain.title.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TitleRepository extends CrudRepository<Title, Long> {
}
