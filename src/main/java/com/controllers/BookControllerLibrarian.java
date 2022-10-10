package com.controllers;

import com.models.Book;
import com.models.Person;
import com.services.BookService;
import com.services.PersonService;
import com.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping("/books")
public class BookControllerLibrarian {
    private final BookValidator bookValidator;
    private final BookService bookService;
    private final PersonService personService;
    private static final String pathTemplate = "booksForReader";

    @Autowired
    public BookControllerLibrarian(BookValidator bookValidator, BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.personService = personService;
    }

    @GetMapping
    public String showAllBooks(Model model){
        model.addAttribute("allBooks",bookService.showAllBooks());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getDetails());
        return pathTemplate + "/allBooks";
    }

    @GetMapping("/books")
    public String showAll(Model model){
        model.addAttribute("allBooks",bookService.showAllBooks());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(Arrays.toString(new Collection[]{authentication.getAuthorities()}));
        return pathTemplate + "/allBooks";
    }

    @GetMapping("/{id}")
    public String showId(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person){
        Book bookId = bookService.findById(id);
        model.addAttribute("book",bookId);
        Person owner = bookId.getPerson_id();
        model.addAttribute("personBusy",owner);
        model.addAttribute("msgWhereBook",owner != null ? "The book is now at: " :
                "Book is free. Who should I assign it to?");
        model.addAttribute("people",personService.showAllPeople());
        return pathTemplate + "/showId";
    }

    @GetMapping("/add")
    public String addBook(@ModelAttribute("book") Book book){
        return pathTemplate + "/add";
    }

    @PostMapping
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult br){
        bookValidator.validate(book,br);
        if (br.hasErrors()){
            return pathTemplate + "/add";
        }
        bookService.createNewBook(book);
        return "redirect:/" + pathTemplate;
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model,@PathVariable("id") int id){
        model.addAttribute("book",bookService.findById(id));
        return pathTemplate + "/edit";
    }

    @PostMapping("editBook/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult br,
                           @PathVariable("id") int id){
        bookValidator.validate(book,br);
        if (br.hasErrors()){
            return pathTemplate + "/edit";
        }
        bookService.update(book,id);
        return "redirect:/" + pathTemplate;
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id){
        bookService.deleteBook(id);
        return "redirect:/" + pathTemplate;
    }

    @PostMapping("/sendBusyBook/{id}")
    public String sendBusyBook(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookService.busyBookById(id, person);
        return "redirect:/" + pathTemplate + "/" + id;
    }

    @PostMapping("/sendFreeBook/{id}")
    public String sendFreeBook(@PathVariable("id") int id){
        bookService.freeBookById(id);
        return "redirect:/" + pathTemplate + "/" + id;
    }

    @GetMapping("/searchBook")
    public String searchPage(Model model){
        model.addAttribute("book",new Book());
        return pathTemplate + "/searchBook";
    }

    @PostMapping("/searchBookByName")
    public String searchBook(@RequestParam("name") String name){
        Book byName = bookService.findByName(name);
        if (byName == null){
            return "redirect:/" + pathTemplate;
        }else {
            return "redirect:/" + pathTemplate + "/" + byName.getId();
        }
    }
}
