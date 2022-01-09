package com.simple.backend.repository;

import com.simple.backend.model.Person;
import com.simple.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);
}
