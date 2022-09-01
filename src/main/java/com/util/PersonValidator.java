package com.util;

import com.dao.PersonDao;
import com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDao.findDuplicate(person).isPresent()){
            System.out.println("Ошибка ввода данных");
            errors.rejectValue("name","","Name is already exists");
            errors.rejectValue("surname","","Surname is already exists");
            errors.rejectValue("patronymic","","Patronymic is already exists");
        }
    }
}
