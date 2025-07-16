package com.imdb.imdb.services;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imdb.imdb.Comment;
import com.imdb.imdb.Film;
import com.imdb.imdb.repos.FilmRepository;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository){
        this.filmRepository=filmRepository;
    }

    public Film insertFilm(Film film){
        return filmRepository.save(film);
    }

    public List<Film>getAllFilms(){
        return filmRepository.findAll();
    }

    public Film getFilmId(ObjectId id){
        return filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));
    }

    public String removeAllFilms(){
        filmRepository.deleteAll();
        return "All films deleted successfully!";
    }

    public String removeFilmById(ObjectId id){
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


    public Film editFilmById(ObjectId id, Film film){
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

    public List<Film> insertFilms(List<Film> films){
        return filmRepository.saveAll(films);
    }

    public List<Film> getFilmTitle(String title){
        return filmRepository.findByTitle(title);
    }

    
    public List<Film> getFilmDirector(String Director){
        return filmRepository.findAllByDirector(Director);
    }

      

}
