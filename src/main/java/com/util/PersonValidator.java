package com.util;

import com.models.Person;
import com.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personService.findDuplicate(person) != null){
            System.out.println("Ошибка ввода данных");
            errors.rejectValue("name","","Name is already exists");
            errors.rejectValue("surname","","Surname is already exists");
            errors.rejectValue("patronymic","","Patronymic is already exists");
        }
    }
}
