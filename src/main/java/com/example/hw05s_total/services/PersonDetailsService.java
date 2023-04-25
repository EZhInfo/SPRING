package com.example.hw05s_total.services;

import com.example.hw05s_total.models.Person;
import com.example.hw05s_total.repositories.PersonRepository;
import com.example.hw05s_total.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;
    
    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Получаем пользователя их таблицы по логину с формы аутентификации
        Optional<Person> person = personRepository.findByLogin(username);
        if (person.isEmpty()) {
            //            исключение будет обработано Spring Security
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new PersonDetails(person.get());
    }
}