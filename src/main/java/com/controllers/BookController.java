package com.controllers;

import com.models.Book;
import com.models.Person;
import com.services.BookService;
import com.services.PersonService;
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
    private final BookValidator bookValidator;
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookValidator bookValidator, BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.personService = personService;
    }

    @GetMapping
    public String showAllBooks(Model model){
        model.addAttribute("allBooks",bookService.showAllBooks());
        return "books/allBooks";
    }

    @GetMapping("/{id}")
    public String showId(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person){
        Book bookId = bookService.findById(id);
        model.addAttribute("book",bookId);
        Person owner = bookId.getOwner();
        model.addAttribute("personBusy",owner);
        model.addAttribute("msgWhereBook",owner != null ? "The book is now at: " :
                "Book is free. Who should I assign it to?");
        model.addAttribute("people",personService.showAllPeople());
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
        bookService.createNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model,@PathVariable("id") int id){
        model.addAttribute("book",bookService.findById(id));
        return "books/edit";
    }

    @PostMapping("editBook/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult br,
                           @PathVariable("id") int id){
        bookValidator.validate(book,br);
        if (br.hasErrors()){
            return "books/edit";
        }
        bookService.update(book,id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/sendBusyBook/{id}")
    public String sendBusyBook(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        bookService.busyBookById(id,person);
        return "redirect:/books/" + id;
    }

    @PostMapping("/sendFreeBook/{id}")
    public String sendFreeBook(@PathVariable("id") int id){
        bookService.freeBookById(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/searchBook")
    public String searchPage(Model model){
        model.addAttribute("book",new Book());
        return "books/searchBook";
    }

    @PostMapping("/searchBookByName")
    public String searchBook(@RequestParam("name") String name){
        Book byName = bookService.findByName(name);
        if (byName == null){
            return "redirect:/books";
        }else {
            return "redirect:/books/" + byName.getId();
        }
    }
}
