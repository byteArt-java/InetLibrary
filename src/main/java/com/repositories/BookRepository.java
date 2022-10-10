package com.repositories;

import com.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>, JpaSpecificationExecutor<Book> {
    Book findByNameAndAuthorAndYearEditions(@NotEmpty(message = "Name should not be empty") String name,
                                            @NotEmpty(message = "Author should not be empty") String author,
                                            @NotEmpty(message = "Year Editions should not be empty") Date yearEditions);
    Book findByName(String name);
}
