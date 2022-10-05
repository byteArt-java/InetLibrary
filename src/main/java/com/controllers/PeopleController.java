package com.controllers;

import com.models.Book;
import com.models.Person;
import com.services.BookService;
import com.services.PersonService;
import com.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;
    private final PersonValidator personValidator;
    private final BookService bookService;
    private static final String pathTemplate = "readers";

    @Autowired
    public PeopleController(PersonService personService, PersonValidator personValidator, BookService bookService) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.bookService = bookService;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("allPeople",personService.showAllPeople());
        return pathTemplate + "/allPeople";
    }

    @GetMapping("/{id}")
    public String showId(@PathVariable("id") int id,Model model){
        Person byId = personService.findById(id);
        model.addAttribute("person",byId);
        List<Book> books = byId.getBooks();
        Date currentDate = new Date();
        StringBuilder sb = new StringBuilder();
        currentDate.setTime(currentDate.getTime() + BookService.PERIOD_USED);
        for (int i = 0; i < books.size() - 1; i++) {
            if (books.get(i).getDate_issued() != null && books.get(i).getDate_issued().before(currentDate) && sb.length() < 1){
                sb.append("Expired book is name: ").append(books.get(i).getName()).append(". ");
            }else if (books.get(i).getDate_issued() != null && books.get(i).getDate_issued().before(currentDate)){
                sb.append(books.get(i).getName()).append(". ");
            }
        }
        model.addAttribute("books",books);
        model.addAttribute("expired",sb.toString());
        model.addAttribute("booksTitle",!byId.getBooks().isEmpty() ? "Books:" :
                "The person did not take a single book");
        return pathTemplate + "/showId";
    }

    @GetMapping("/add")
    public String addPerson(@ModelAttribute("person") Person person){
        return pathTemplate + "/add";
    }

    @PostMapping
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult br){
        personValidator.validate(person,br);
        if (br.hasErrors()){
            return pathTemplate + "/add";
        }
        personService.createNewPerson(person);
        return "redirect:/" + pathTemplate;
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personService.findById(id));
        return pathTemplate + "/edit";
    }

    @PostMapping("editPerson/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person, BindingResult br,
                             @PathVariable("id") int id){
        personValidator.validate(person,br);
        if (br.hasErrors()){
            return pathTemplate + "/edit";
        }
        personService.update(person,id);
        return "redirect:/" + pathTemplate;
    }

    @GetMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") int id){
        Person person = personService.findById(id);
        List<Book> books = person.getBooks();
        for (Book book : books) {
            book.setOwner(null);
            bookService.save(book);
        }
        personService.deletePerson(person.getId());
        return "redirect:/" + pathTemplate;
    }
}
