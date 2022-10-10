CREATE DATABASE IF NOT EXISTS library COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS test COLLATE utf8mb4_unicode_ci;

USE library;

DROP TABLE IF EXISTS book;

CREATE TABLE book(
                     id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
                     person_id INT,
                     name VARCHAR(100) NOT NULL,
                     author VARCHAR(100) NOT NULL,
                     yearEditions DATE NOT NULL,
                     date_expiration TIMESTAMP,
                     FOREIGN KEY (person_id) REFERENCES person(id)
);

INSERT INTO book(name, author, yearEditions)
VALUES ('Inside the Machine', 'Jon Stokes', '2015-05-25'),
       ('Structure and Interpretation of Computer Programs', 'Harold Abelson', '2010-10-12'),
       ('Design Patterns', 'Erich Gamma', '2020-12-12'),
       ('Programming Pearls', 'Jon Bentley', '2019-01-15'),
       ('Code Simplicity: The Fundamentals of Software', 'Max Kanat-Alexander', '2020-10-03'),
       ('Introduction to Algorithms', 'Thomas H. Cormen', '2019-03-04');