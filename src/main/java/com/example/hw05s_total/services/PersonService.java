package com.example.hw05s_total.services;

import com.example.hw05s_total.enumm.Role;
import com.example.hw05s_total.models.Person;
import com.example.hw05s_total.repositories.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Person findByLogin(Person person) {
        Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
        return person_db.orElse(null);
    }
    
    public Person getPersonById(int id) {
        Optional<Person> person_db = personRepository.findById(id);
        return person_db.orElse(null);
    }
    
    @Transactional
    public void registerPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(Role.USER.getTypeRole());
        personRepository.save(person);
    }
    
    @Transactional
    public void updatePerson(int id, Person person) {
        person.setId(id);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }
    
    @Transactional
    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
    
    public List<Person> getAllUsers(){
        return personRepository.findAll();
    }
}
