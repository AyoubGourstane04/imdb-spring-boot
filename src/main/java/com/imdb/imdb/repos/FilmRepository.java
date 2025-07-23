package com.imdb.imdb.repos;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.imdb.imdb.Film;



public interface FilmRepository extends MongoRepository<Film,String>{

    public List<Film> findByTitle(String title);

    public List<Film> findAllByDirector(String Director);
    
}
