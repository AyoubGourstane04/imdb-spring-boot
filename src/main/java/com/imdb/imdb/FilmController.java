package com.imdb.imdb;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @PostMapping
    public void AddNewFilm(@RequestBody Film film){
        filmService.insertFilm(film);
    }

    // dir liha hka fach bitdir request :POST http://localhost:8080/films/bulk
    @PostMapping("/bulk")
    public void addFilms(@RequestBody List<Film> films){
            filmService.insertFilms(films);
    }

    @GetMapping
    public List<Film> getFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("{id}")
    public Film getFilmById(@PathVariable Integer id){
        return filmService.getFilmId(id);
    }

    @DeleteMapping
    public void DeleteAllFilms(){
        filmService.RemoveAllFilms();
    }

    @DeleteMapping("{id}")
    public void DeleteFilmById(@PathVariable Integer id){
        filmService.RemoveFilmById(id);
    }

    @PutMapping("{id}")
    public void updateFilmById(@PathVariable Integer id, @RequestBody Film film){
        filmService.editFilmById(id,film);
    }

    // dir liha hka fach bitdir request :PUT http://localhost:8080/films/Title/like
    @PutMapping("{Title}/like")
    public void IncrementLikesCount(@PathVariable String Title){
        filmService.updateLikes(Title);
    }

    /* dir liha hka fach bitdir request :PUT http://localhost:8080/films/Title/comment
        ou flbody lcomment ikon ghir text 3adi bach it9ra ka string bla ""
    */
    @PutMapping("{Title}/comment")
    public void AddComment(@PathVariable String Title,@RequestBody String comment){
        filmService.InsertComment(Title,comment);
    }






}
