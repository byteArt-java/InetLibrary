package com.services;

import com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final com.repositories.PersonRepository personRepository;

    @Autowired
    public PersonService(com.repositories.PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> showAllPeople(){
        List<Person> list = personRepository.findAll();
        return list;
    }

    public Person findById(int id){
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void createNewPerson(Person person){
        personRepository.save(person);
    }

    public Person findDuplicate(Person person){
        return personRepository.findByNameAndSurnameAndPatronymic(person.getName(),
                person.getSurname(), person.getPatronymic());
    }

    @Transactional
    public void update(Person person, int id){//null don't have in this method
        Person byId = personRepository.findById(id).orElse(null);
        byId.setName(person.getName());
        byId.setSurname(person.getSurname());
        byId.setPatronymic(person.getPatronymic());
        byId.setBirthday(person.getBirthday());
        personRepository.save(byId);
    }

    @Transactional
    public void deletePerson(int id) {
        personRepository.delete(personRepository.findById(id).orElse(null));
    }
}
