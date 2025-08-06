package com.imdb.imdb.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imdb.imdb.Film;
import com.imdb.imdb.services.FilmService;

@CrossOrigin(origins = {"http://127.0.0.1:8080", "http://localhost:8080"}, maxAge = 3600)
@RestController
@EnableMethodSecurity
@RequestMapping("users/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @PostMapping("/add")
    public Film addNewFilm(@RequestParam("film") String film, @RequestParam("image") MultipartFile image)throws IOException{
        return filmService.insertFilm(film, image);
    }

    @PostMapping("/bulk")
    public List<Film> addFilms(@RequestParam("films") String films, @RequestParam("images") MultipartFile[] images)
                                                    throws JsonProcessingException,IOException{
            return filmService.insertFilms(films, images);
    }

    @GetMapping("/all")
    public List<Film> getFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable String id){
        return filmService.getFilmById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAll")
    public String deleteAllFilms(){
        return filmService.removeAllFilms();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteFilmById(@PathVariable String id){
        return filmService.removeFilmById(id);
    }

    @PutMapping("/update/{id}")
    public Film updateFilmById(@PathVariable String id, @RequestBody Film film){
        return filmService.editFilmById(id,film);
    }

    @PutMapping("/like/{id}")
    public Film incrementLikesCount(@PathVariable String id){
        return filmService.updateLikes(id);
    }

    
    @PutMapping("/comment/{id}")
    public Film addComment(@PathVariable String id,@RequestBody String comment){
        return filmService.insertComment(id,comment);
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
