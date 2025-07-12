package com.imdb.imdb;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository){
        this.filmRepository=filmRepository;
    }

    public void insertFilm(Film film){
        filmRepository.save(film);
    }

    public List<Film>getAllFilms(){
        return filmRepository.findAll();
    }

    public Film getFilmId(ObjectId id){
        return filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));
    }

    public void removeAllFilms(){
        filmRepository.deleteAll();
    }

    public void removeFilmById(ObjectId id){
        boolean exists = filmRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found.");
        }

        filmRepository.deleteById(id);
    }

    public String checkString(String str){
        if(str == null || !str.matches("^[A-Za-z0-9 ]+$")){
            throw new IllegalArgumentException("Invalid format!");
        }else{
            return str;
        }
    }


    public void editFilmById(ObjectId id, Film film){
        Film oldFilm = filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));


        oldFilm.setTitle(checkString(film.getTitle()));
        oldFilm.setDirector(checkString(film.getDirector()));

        oldFilm.setMainCast(film.getMainCast());

        oldFilm.setCommentsCount(film.getCommentsCount());
        oldFilm.setLikesCount(film.getLikesCount());
        oldFilm.setComments(film.getComments());

        filmRepository.save(oldFilm);
    }

    public void updateLikes(String title){
        Film film = filmRepository.findByTitle(title);

        if(film == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, title + " not found.");
        }

        Integer newLikes=film.getLikesCount();
        newLikes++;

        film.setLikesCount(newLikes);

        filmRepository.save(film);
    }

    public void insertComment(String title,String Comment){
        Film film = filmRepository.findByTitle(title);

        if(film == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, title + " not found.");
        }

        Comment comment = new Comment(checkString(Comment),LocalDateTime.now());
        film.getComments().add(comment);

        Integer newComments=film.getCommentsCount();
        newComments++;

        film.setCommentsCount(newComments);

        filmRepository.save(film);
    }

    public void insertFilms(List<Film> films){
        filmRepository.saveAll(films);
    }

      

}
