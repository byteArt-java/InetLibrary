package com.util;

import com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    @Autowired

    @Override public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override public void validate(Object target, Errors errors) {

    }
}
