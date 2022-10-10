CREATE DATABASE IF NOT EXISTS library COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS test COLLATE utf8mb4_unicode_ci;

USE library;

DROP TABLE IF EXISTS person;

CREATE TABLE person(
                       id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       surname VARCHAR(100) NOT NULL,
                       patronymic VARCHAR(100) NOT NULL,
                       birthday DATE NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       banned BIT(1));

INSERT INTO person(name, surname, patronymic, birthday, email, password, role, banned)
VALUES ('Artem', 'Kolchanov', 'Andreevich', '1988-01-19', 'geodeticstroi@mail.ru',
        '$2a$12$iNJfPVTXV3LYLGa/C6rLGu7B9KWGaYHHr58FhvIHu4dAEhPpoC0oS', 'ADMIN', false);

INSERT INTO person(name, surname, patronymic, birthday, email, password, role, banned)
VALUES ('Stas','Smirnov', 'Petrovich', '1990-05-19', 'stas8047@mail.ru',
        '$2a$12$iNJfPVTXV3LYLGa/C6rLGu7B9KWGaYHHr58FhvIHu4dAEhPpoC0oS', 'READER', false);

INSERT INTO person(name, surname, patronymic, birthday, email, password, role, banned)
VALUES ('Katya','Bubnova', 'Samsonova', '1995-03-25', 'katymeou32@mail.ru',
        '$2a$12$iNJfPVTXV3LYLGa/C6rLGu7B9KWGaYHHr58FhvIHu4dAEhPpoC0oS', 'LIBRARIAN', false);