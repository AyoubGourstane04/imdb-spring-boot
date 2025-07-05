package com.imdb.imdb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmRepository extends MongoRepository<Film,Integer>{

}
