package com.controllers;

import com.dao.PersonBooksDao;
import com.dao.PersonDao;
import com.models.Book;
import com.models.Person;
import com.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;
    private final PersonValidator personValidator;
    private final PersonBooksDao personBooksDao;

    @Autowired
    public PeopleController(PersonDao personDao, PersonValidator personValidator, PersonBooksDao personBooksDao) {
        this.personDao = personDao;
        this.personValidator = personValidator;
        this.personBooksDao = personBooksDao;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("allPeople",personDao.showAllPeople());
        return "people/allPeople";
    }

    @GetMapping("/{id}")
    public String showId(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personDao.findById(id));
        List<Book> listBooks = personBooksDao.getBooksForPerson(id);
        model.addAttribute("books",listBooks);
        model.addAttribute("booksTitle",!listBooks.isEmpty() ? "Books:" :
                "The person did not take a single book");
        return "people/showId";
    }

    @GetMapping("/add")
    public String addPerson(@ModelAttribute("person") Person person){
        return "people/add";
    }

    @PostMapping
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult br){
        personValidator.validate(person,br);
        if (br.hasErrors()){
            return "people/add";
        }
        personDao.createNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDao.findById(id));
        return "people/edit";
    }

    @PostMapping("editPerson/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person,BindingResult br,
                             @PathVariable("id") int id){
        personValidator.validate(person,br);
        if (br.hasErrors()){
            return "people/edit";
        }
        personDao.update(person,id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") int id){
        personDao.deletePerson(id);
        return "redirect:/people";
    }
}
