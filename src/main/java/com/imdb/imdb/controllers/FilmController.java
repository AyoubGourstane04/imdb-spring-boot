package com.imdb.imdb.controllers;

import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.imdb.Film;
import com.imdb.imdb.services.FilmService;


@RestController
@RequestMapping("users/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @PostMapping("/add")
    public Film addNewFilm(@RequestBody Film film){
        return filmService.insertFilm(film);
    }

    @PostMapping("/bulk")
    public List<Film> addFilms(@RequestBody List<Film> films){
            return filmService.insertFilms(films);
    }

    @GetMapping("/all")
    public List<Film> getFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable ObjectId id){
        return filmService.getFilmId(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllFilms(){
        return filmService.removeAllFilms();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFilmById(@PathVariable ObjectId id){
        return filmService.removeFilmById(id);
    }

    @PutMapping("/update/{id}")
    public Film updateFilmById(@PathVariable ObjectId id, @RequestBody Film film){
        return filmService.editFilmById(id,film);
    }

    @PutMapping("/like/{Title}")
    public List<Film> incrementLikesCount(@PathVariable String Title){
        return filmService.updateLikes(Title);
    }

    
    @PutMapping("/comment/{Title}")
    public List<Film> addComment(@PathVariable String Title,@RequestBody String comment){
        return filmService.insertComment(Title,comment);
    }

    @GetMapping("/title/{Title}")
    public List<Film> getFilmByTitle(@PathVariable String Title){
        return filmService.getFilmTitle(Title);
    }

    @GetMapping("/director/{Director}")
    public List<Film> getFilmByDirector(@PathVariable String Director){
        return filmService.getFilmDirector(Director);
    }
    






}
