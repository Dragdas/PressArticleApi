package com.kkulpa.pressarticleapi.app.repository;

import com.kkulpa.pressarticleapi.app.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName); //ToDo zmienic na optional


}
