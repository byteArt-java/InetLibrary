package com.dao;

import com.models.Book;
import com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAllBooks(){
        return jdbcTemplate.query("SELECT * FROM library.book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> findDuplicate(Book book){
        return jdbcTemplate.query("SELECT * FROM library.book WHERE name=? AND author=? AND yearEditions=?",
                new Object[]{book.getName(),book.getAuthor(),book.getYearEditions()},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void createNewBook(Book book){
        jdbcTemplate.update("INSERT INTO library.book(name,author,yearEditions) " +
                "VALUES(?,?,?)",book.getName(),book.getAuthor(),book.getYearEditions());
    }

    public Book findById(int id){
        return jdbcTemplate.query("SELECT * FROM library.book WHERE id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().filter(p->p.getId() == id).
                findAny().orElse(null);
    }

    public void update(Book book,int id){
        jdbcTemplate.update("UPDATE library.book SET name=?,author=?,yearEditions=? WHERE id=?",
                book.getName(),book.getAuthor(),book.getYearEditions(),id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM library.book WHERE id=?",id);
    }
}
