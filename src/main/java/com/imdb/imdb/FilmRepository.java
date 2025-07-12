package com.imdb.imdb;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;



public interface FilmRepository extends MongoRepository<Film,ObjectId>{

    @Query("{'Title' : ?0 }")
    public Film findByTitle(String title);
}
