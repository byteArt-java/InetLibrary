package com.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class Book {
    private int id;
    @NotNull(message = "Name should not be empty")
    private String name;
    @NotNull(message = "Author should not be empty")
    private String author;
    @Pattern(regexp = "1{1}[8-9]{1}[0-9]{1}[0-9]{1}|2{1}0{1}[0-2]{1}[0-9]{1}",message = "invalid yearEditions")
    private LocalDate yearEditions;

    public Book(int id,String name,String author,LocalDate yearEditions) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearEditions = yearEditions;
    }

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getYearEditions() {
        return yearEditions;
    }

    public void setYearEditions(LocalDate yearEditions) {
        this.yearEditions = yearEditions;
    }
}
