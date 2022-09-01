package com.controllers;

import com.dao.BookDao;
import com.dao.PersonBooksDao;
import com.dao.PersonDao;
import com.models.Book;
import com.models.Person;
import com.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final BookValidator bookValidator;
    private final PersonBooksDao personBooksDao;
    private final PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, BookValidator bookValidator, PersonBooksDao personBooksDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.bookValidator = bookValidator;
        this.personBooksDao = personBooksDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String showAllBooks(Model model){
        model.addAttribute("allBooks",bookDao.showAllBooks());
        return "books/allBooks";
    }

    @GetMapping("/{id}")
    public String showId(Model model,@PathVariable("id") int id,@ModelAttribute("person") Person person){
        model.addAttribute("book",bookDao.findById(id));
        Person personFromDB = personBooksDao.getBooksForBusyPerson(id);
        model.addAttribute("personBusy",personFromDB);
        model.addAttribute("msgWhereBook",personFromDB != null ? "The book is now at: " :
                "Book is free. Who should I assign it to?");
        model.addAttribute("people",personDao.showAllPeople());
        return "books/showId";
    }

    @GetMapping("/add")
    public String addBook(@ModelAttribute("book") Book book){
        return "books/add";
    }

    @PostMapping
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult br){
        bookValidator.validate(book,br);
        if (br.hasErrors()){
            return "books/add";
        }
        bookDao.createNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model,@PathVariable("id") int id){
        model.addAttribute("book",bookDao.findById(id));
        return "books/edit";
    }

    @PostMapping("editBook/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult br,
                             @PathVariable("id") int id){
        bookValidator.validate(book,br);
        if (br.hasErrors()){
            return "books/edit";
        }
        bookDao.update(book,id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id){
        bookDao.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/sendBusyBook/{id}")
    public String sendBusyBook(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        personBooksDao.busyBookById(id,person.getId());
        return "redirect:/books/" + id;
    }

    @PostMapping("/sendFreeBook/{id}")
    public String sendFreeBook(@PathVariable("id") int id){
        personBooksDao.freeBookById(id);
        return "redirect:/books/" + id;
    }
}
