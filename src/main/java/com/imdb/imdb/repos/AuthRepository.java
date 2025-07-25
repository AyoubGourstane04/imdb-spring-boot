package com.imdb.imdb.repos;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.imdb.imdb.User;



public interface AuthRepository extends MongoRepository<User,ObjectId>{
    Optional<User> findByUsername(String username);
    // Optional<User> findByResetToken(String resetToken);
}
