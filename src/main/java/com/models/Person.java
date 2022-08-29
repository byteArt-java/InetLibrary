package com.models;


import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    private int id;
    @NotNull(message = "Name should not be empty")
    private String name;
    @NotNull(message = "Surname should not be empty")
    private String surname;
    @NotNull(message = "Patronymic should not be empty")
    private String patronymic;
    @Pattern(regexp = "1{1}[9]{1}[7-9]{1}[0-9]{1}|2{1}0{1}[0-2]{1}[0-9]{1}",message = "invalid yearOfBirth")
    private LocalDate yearOfBirth;

    public Person(int id,String name,String surname,String patronymic,LocalDate yearOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(){

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(LocalDate yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
