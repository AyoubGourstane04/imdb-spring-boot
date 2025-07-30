package com.imdb.imdb.controllers;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/static")
public class StaticContentController {
    

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/updatePassword/{id}")
    public String showUpdatePasswordPage(@PathVariable ObjectId id){
        return "updatePassword";
    }

}