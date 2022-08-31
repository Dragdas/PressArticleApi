package com.kkulpa.pressarticleapi.repository;

import com.kkulpa.pressarticleapi.app.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
