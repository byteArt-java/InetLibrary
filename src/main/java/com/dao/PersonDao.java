package com.dao;

import com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAllPeople(){
        return jdbcTemplate.query("SELECT * FROM library.person;",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(int id){
        return jdbcTemplate.query("SELECT * FROM library.person WHERE id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().filter(p->p.getId() == id).
                findAny().orElse(null);
    }

    public void createNewPerson(Person person){
        jdbcTemplate.update("INSERT INTO library.person(name,surname,patronymic,birthday) " +
                "VALUES(?,?,?,?)",person.getName(),person.getSurname(),person.getPatronymic(),person.getBirthday());
    }

    public Optional<Person> findDuplicate(Person person){
        return jdbcTemplate.query("SELECT * FROM library.person WHERE name=? AND surname=? AND patronymic=?",
                new Object[]{person.getName(),person.getSurname(),person.getPatronymic()},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void update(Person person,int id){
        jdbcTemplate.update("UPDATE library.person SET name=?,surname=?,patronymic=?,birthday=? WHERE id=?",
                person.getName(),person.getSurname(),person.getPatronymic(),person.getBirthday(),id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM library.person WHERE id=?",id);
    }
}
