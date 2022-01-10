package com.simple.backend.controller;

import com.simple.backend.model.Person;
import com.simple.backend.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("person")
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    PersonRepository personRepository;

    @PostMapping("save")
    public Person save(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("{id}")
    public Person find(@PathVariable("id") Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @GetMapping("delete/{id}")
    public Long delete(@PathVariable("id") Long id) {
        personRepository.deleteById(id);
        return id;
    }

    @GetMapping("all")
    public List<Person> all() {
        return personRepository.findAll();
    }
}
