package com.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person person_id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Author should not be empty")
    private String author;

    @Column(name = "yearEditions")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date yearEditions;

    @Column(name = "date_expiration")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date_expiration;

    public Book(){

    }

    public Book(int id, String name, String author, Date yearEditions) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearEditions = yearEditions;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", person_id=" + person_id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearEditions=" + yearEditions +
                ", date_expiration=" + date_expiration +
                '}';
    }
}
