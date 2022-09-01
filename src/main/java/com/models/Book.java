package com.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class Book {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Author should not be empty")
    private String author;
    @Pattern(regexp = "((1{1}[9]{1}[7-9]{1}[1-9]{1})[\\-]([0]{1}[1-9]{1}|[1]{1}[1-2]{1})[\\-]([1]{1}[1-2]{1}|[0-3]{1}[1-9]{1}))|((2{1}[0]{1}[0-2]{1}[1-9]{1})[\\-]([0]{1}[1-9]{1}|[1]{1}[1-2]{1})[\\-]([1]{1}[1-2]{1}|[0-3]{1}[1-9]{1}))",message = "invalid yearEditions")
    private String yearEditions;

    public Book(int id,String name,String author,String yearEditions) {
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

    public String getYearEditions() {
        return yearEditions;
    }

    public void setYearEditions(String yearEditions) {
        this.yearEditions = yearEditions;
    }

    @Override public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", author='" + author + '\'' + ", yearEditions='" + yearEditions + '\'' + '}';
    }
}
