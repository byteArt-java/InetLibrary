package com.models;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "Username should not be empty")
    private String username;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    @Column(name = "patronymic")
    @NotEmpty(message = "Patronymic should not be empty")
    private String patronymic;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email incorrect")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @Column(name = "role")
    @NotEmpty(message = "Password should not be empty")
    private String role;

    @Column(name = "banned")
    @NotEmpty(message = "Banned should not be empty")
    private Boolean banned;

    @OneToMany(mappedBy = "person_id",fetch = FetchType.EAGER)
    private List<Book> books;

    public Person(int id, String username, String name, String surname, String patronymic,
                  Date birthday, String email, String password, String role, Boolean banned) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.role = role;
        this.banned = banned;
    }

    public Person(){

    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", banned=" + banned +
                '}';
    }
}
