package com.imdb.imdb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public Film getFilmId(Integer id){
        return filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));
    }

    public void RemoveAllFilms(){
        filmRepository.deleteAll();
    }

    public void RemoveFilmById(Integer id){
        boolean exists = filmRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found.");
        }

        filmRepository.deleteById(id);
    }



    public void editFilmById( Integer id, Film film){
        Film oldFilm = filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));

        oldFilm.setTitle(film.getTitle());
        oldFilm.setDirector(film.getDirector());
        oldFilm.setMainCast(film.getMainCast());
        oldFilm.setCommentsCount(film.getCommentsCount());
        oldFilm.setLikesCount(film.getLikesCount());
        oldFilm.setComments(film.getComments());

        filmRepository.save(oldFilm);
    }


    public void updateLikes(String title){
        List<Film> films = getAllFilms();
        Film film=null;
        for(Film movie : films){
            if(title.equals(movie.getTitle())){
                film = movie;
            }
        }

        if(film == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, title + " not found.");
        }

        Integer newLikes=film.getLikesCount();
        newLikes++;

        film.setLikesCount(newLikes);

        filmRepository.save(film);
    }

    public void InsertComment(String title,String Comment){
        List<Film> films = getAllFilms();
        Film film=null;
        for(Film movie : films){
            if(title.equals(movie.getTitle())){
                film = movie;
            }
        }

        if(film == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, title + " not found.");
        }

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formdate = DateTimeFormatter.ofPattern("E, dd-MM-yyyy HH:mm:ss");
        String newDate = date.format(formdate);

        Comment comment = new Comment(Comment,newDate);

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
