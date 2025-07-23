package com.imdb.imdb.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.bson.json.JsonObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imdb.imdb.Comment;
import com.imdb.imdb.Film;
import com.imdb.imdb.Image;
import com.imdb.imdb.repos.FilmRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final FileUploadService fileUploadService;



    public Film insertFilm(JsonObject filmJson, MultipartFile image) throws IOException{

        ObjectMapper objMap = new ObjectMapper();

        Film film = objMap.readValue(filmJson.toString(), Film.class);        

        String id = filmRepository.save(film).getId();

        Film savedFilm = insertImageById(id, image);

        return savedFilm;
    }

    public List<Film> insertFilms(String filmsJson, MultipartFile[] images) throws JsonProcessingException,IOException{

        ObjectMapper objMap = new ObjectMapper();

        List<Film> films = Arrays.asList(objMap.readValue(filmsJson,Film[].class));

        int i=0;

        List<Film> filmsList = new ArrayList<>();
        
        for(Film film : films){
            String id = filmRepository.save(film).getId();

            filmsList.add(insertImageById(id, images[i++]));
        }

        return filmsList;
    }


    public List<Film>getAllFilms(){
        return filmRepository.findAll();
    }

    public Film getFilmById(String id){
        return filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));
    }

    public String removeAllFilms(){
        filmRepository.deleteAll();
        return "All films deleted successfully!";
    }

    public String removeFilmById(String id){
        boolean exists = filmRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found.");
        }

        filmRepository.deleteById(id);

        return "Film deleted successfully!";
    }

    public String checkString(String str){
        if(str == null || !str.matches("^[A-Za-z0-9 !?,.']+$")){
            throw new IllegalArgumentException("Invalid format!");
        }else{
            return str;
        }
    }


    public Film editFilmById(String id, Film film){
        Film oldFilm = filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));


        oldFilm.setTitle(checkString(film.getTitle()));
        oldFilm.setDirector(checkString(film.getDirector()));

        oldFilm.setMainCast(film.getMainCast());

        oldFilm.setCommentsCount(film.getCommentsCount());
        oldFilm.setLikesCount(film.getLikesCount());
        oldFilm.setComments(film.getComments());

        return filmRepository.save(oldFilm);
    }

    public List<Film> updateLikes(String title){
        List<Film> movies = filmRepository.findByTitle(title);

        if(movies.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, title + " not found.");
        }
    
        for(Film film : movies){
            Integer newLikes=film.getLikesCount();
            newLikes++;

            film.setLikesCount(newLikes);
        
        }
       
        return filmRepository.saveAll(movies);
    }

    public List<Film> insertComment(String title,String Comment){
       List<Film> movies = filmRepository.findByTitle(title);

        if(movies.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, title + " not found.");
        }
    
        Comment comment = new Comment(checkString(Comment),LocalDateTime.now());

        for(Film film : movies){
            film.getComments().add(comment);

            Integer newComments=film.getCommentsCount();
            newComments++;

            film.setCommentsCount(newComments);
        }
       
        return filmRepository.saveAll(movies);
    }


    public List<Film> getFilmTitle(String title){
        return filmRepository.findByTitle(title);
    }

    
    public List<Film> getFilmDirector(String Director){
        return filmRepository.findAllByDirector(Director);
    }


    public Film insertImageById(String id, MultipartFile image) throws IOException{

        String imagePath = fileUploadService.uploadFile(image);

        Film film = getFilmById(id);

        Image img = new Image();
        img.setImageName(image.getOriginalFilename());
        img.setImagePath(imagePath);

        film.setImage(img);

        return filmRepository.save(film);
    }
      

}
