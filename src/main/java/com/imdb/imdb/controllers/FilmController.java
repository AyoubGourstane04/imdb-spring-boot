package com.imdb.imdb.controllers;

import java.io.IOException;
import java.util.List;

import org.bson.json.JsonObject;
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


@RestController
@RequestMapping("users/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @PostMapping("/add")
    public Film addNewFilm(@RequestParam("film") JsonObject film, @RequestParam("image") MultipartFile image)throws IOException{
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

    @DeleteMapping("/deleteAll")
    public String deleteAllFilms(){
        return filmService.removeAllFilms();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFilmById(@PathVariable String id){
        return filmService.removeFilmById(id);
    }

    @PutMapping("/update/{id}")
    public Film updateFilmById(@PathVariable String id, @RequestBody Film film){
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

    // @PostMapping("/{id}/image")
    // public Film addImageById(@PathVariable String id, @RequestBody MultipartFile image) throws IOException{
    //     return filmService.insertImageById(id,image);
    // }

    // @GetMapping("/{id}/image")
    // public String getImageById(@PathVariable String id){
    //     return filmService.getImageById(id);
    // }
    






}
