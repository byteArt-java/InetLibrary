package com.services;

import com.models.Book;
import com.models.Person;
import com.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookService {
    public static final long PERIOD_USED = 60000;//1 минута
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findDuplicate(Book book) {
        return bookRepository.findByNameAndAuthorAndYearEditions(book.getName(),book.getAuthor(),book.getYearEditions());
    }

    public List<Book> showAllBooks(){
        return bookRepository.findAll(PageRequest.of(0,10, Sort.by("yearEditions"))).getContent();
    }

    public Book findByName(String name){
        return bookRepository.findByName(name);
    }

    public List<Book> findAllBooksForPerson(Person person){
        Iterable<Integer> iterable = new ArrayList<>(Collections.singletonList(person.getId()));
        return bookRepository.findAllById(iterable);
    }

    @Transactional
    public void createNewBook(Book book){
        bookRepository.save(book);
    }

    public Book findById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Book book,int id){
        Book byId = bookRepository.findById(id).orElse(null);
        byId.setName(book.getName());
        byId.setAuthor(book.getAuthor());
        byId.setYearEditions(book.getYearEditions());
    }

    @Transactional
    public void deleteBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        bookRepository.delete(book);
    }

    @Transactional
    public void busyBookById(int idBook, Person person) {
        Book book = bookRepository.findById(idBook).orElse(null);
        book.setPerson_id(person);
//        book.setDate_issued(new Date());
        bookRepository.save(book);
    }

    @Transactional
    public void freeBookById(int idBook) {
        Book book = bookRepository.findById(idBook).orElse(null);
        book.setPerson_id(null);
//        book.setDate_issued(null);
        bookRepository.save(book);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
}
