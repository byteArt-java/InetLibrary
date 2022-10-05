package com.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Author should not be empty")
    private String author;

    @Column(name = "yearEditions")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate yearEditions;

    @Column(name = "date_expiration")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate date_expiration;

    @ManyToOne
    @JoinColumn(name = "reader_id",referencedColumnName = "id")
    private Person owner;

    public Book(){

    }

    public Book(int id, String name, String author, LocalDate yearEditions,
                LocalDate date_expiration, Person owner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearEditions = yearEditions;
        this.date_expiration = date_expiration;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearEditions=" + yearEditions +
                ", date_expiration=" + date_expiration +
                ", owner=" + owner +
                '}';
    }
}
