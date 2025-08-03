package com.imdb.imdb.controllers;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = {"http://127.0.0.1:8080", "http://localhost:8080"}, maxAge = 3600)
@Controller
public class StaticContentController {
    

    @GetMapping("static/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("static/updatePassword/{id}")
    public String showUpdatePasswordPage(@PathVariable ObjectId id){
        return "updatePassword";
    }

    @GetMapping("static/index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("static/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("static/addFilm")
    public String getAddFilmPage() {
        return "addFilm";
    }

    @GetMapping("static/movieDetails/{id}")
    public String getMovieDetailsPage(@PathVariable String id) {
        return "movieDetails";
    }

    @GetMapping("static/usersPage")
    public String getUsersPage() {
        return "usersPage";
    }

}