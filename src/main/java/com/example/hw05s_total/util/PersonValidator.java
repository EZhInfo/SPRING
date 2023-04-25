package com.example.hw05s_total.util;

import com.example.hw05s_total.models.Person;
import com.example.hw05s_total.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    
    private final PersonService personService;
    
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }
    
    // валидатор модели
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personService.findByLogin(person) != null) {
          errors.rejectValue("login", "", "Такой логин уже зарегистрирован");
        }
        
    }
}
