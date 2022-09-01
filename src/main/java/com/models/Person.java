package com.models;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Surname should not be empty")
    private String surname;
    @NotEmpty(message = "Patronymic should not be empty")
    private String patronymic;
    @Pattern(regexp = "((1{1}[9]{1}[7-9]{1}[1-9]{1})[\\-]([0]{1}[1-9]{1}|[1]{1}[1-2]{1})[\\-]([1]{1}[1-2]{1}|[0-3]{1}[1-9]{1}))|((2{1}[0]{1}[0-2]{1}[1-9]{1})[\\-]([0]{1}[1-9]{1}|[1]{1}[1-2]{1})[\\-]([1]{1}[1-2]{1}|[0-3]{1}[1-9]{1}))",message = "Date is not valid")
    private String birthday;

    public Person(int id,String name,String surname,String patronymic,String yearOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = yearOfBirth;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", patronymic='" + patronymic + '\'' + ", birthday=" + birthday + '}';
    }
}
