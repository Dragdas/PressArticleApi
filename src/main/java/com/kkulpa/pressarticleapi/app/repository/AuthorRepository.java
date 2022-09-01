package com.kkulpa.pressarticleapi.app.repository;

import com.kkulpa.pressarticleapi.app.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);


}
