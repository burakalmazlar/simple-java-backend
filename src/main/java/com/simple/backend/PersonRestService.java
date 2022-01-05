package com.simple.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonRestService {

    PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/person/save")
    public Person save(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/person/{id}")
    public Person find(@PathVariable("id") Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @GetMapping("/person/delete/{id}")
    public Long delete(@PathVariable("id") Long id) {
        personRepository.deleteById(id);
        return id;
    }

    @GetMapping("/persons")
    public List<Person> all() {
        return personRepository.findAll();
    }
}
