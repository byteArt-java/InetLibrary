package com.controllers;

import com.models.Book;
import com.models.Person;
import com.util.PersonValidator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/people")
public class PeopleController {

}
