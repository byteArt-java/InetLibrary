package com.dao;

import com.models.Book;
import com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonBooksDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonBooksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksForPerson(int idPerson){
        List<Integer> booksId = jdbcTemplate.query("SELECT id_book FROM library.person_book WHERE id_person=?",
                new MyIdRowMapper(),idPerson);
        List<Book> books = new ArrayList<>();
        for (Integer integer : booksId) {
            books.add(jdbcTemplate.query("SELECT * FROM library.book WHERE id=?",
                    new BeanPropertyRowMapper<>(Book.class),integer).stream().
                    findFirst().orElse(null));
        }
        return books;
    }

    public Person getBooksForBusyPerson(int idBook){
        Integer idPerson = jdbcTemplate.query("SELECT id_person FROM library.person_book WHERE id_book=?",
                new MyIdRowMapper(),idBook).stream().findFirst().orElse(0);
        return jdbcTemplate.query("SELECT * FROM library.person WHERE id=?",
                new BeanPropertyRowMapper<>(Person.class),idPerson).stream().findFirst().orElse(null);
    }

    public void freeBookById(int id) {
        jdbcTemplate.update("DELETE FROM library.person_book WHERE id_book=?",id);
    }

    public void busyBookById(int idBook, int idPerson) {
        jdbcTemplate.update("INSERT INTO library.person_book(id_person,id_book) VALUES(?,?)",idPerson,idBook);
    }

    //mysql.driver=com.mysql.cj.jdbc.Driver
    //mysql.url=jdbc:mysql://localhost:3306/library
    //mysql.login=root
    //mysql.password=mysql
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root",
//                "mysql");
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "SELECT id_book FROM library.person_book WHERE id_person=?");
//        preparedStatement.setInt(1,1);
//        ResultSet rs = preparedStatement.executeQuery();
//        while (rs.next()){
//            System.out.println(rs.getString("id_book"));
//        }
//
//    }
}
