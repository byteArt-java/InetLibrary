package com.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectionController {

    @GetMapping
    private static String whereToSendTheAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object[] objects = authentication.getAuthorities().toArray();
        if (objects[0] != null){
            switch (objects[0].toString()){
                case "ROLE_LIBRARIAN":return "/booksForLibrarian/allBooks";
                case "ROLE_ADMIN":return "/admin/pageManagement";
                case "ROLE_READER":return "/booksForReader/allBooks";
            }
        }
        return "/unknownRole";
    }
}
