package com.repositories;

import com.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer>, JpaSpecificationExecutor<Person> {
    Optional<Person> findByNameAndSurnameAndPatronymic(@NotEmpty(message = "Name should not be empty") String name,
                                             @NotEmpty(message = "Surname should not be empty") String surname,
                                             @NotEmpty(message = "Patronymic should not be empty") String patronymic);
//    @Query("select u from Person u where u.name=?1")
    Optional<Person> findByUsername(String username);
}
