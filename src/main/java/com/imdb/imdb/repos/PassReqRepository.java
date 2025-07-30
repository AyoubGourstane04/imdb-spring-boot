package com.imdb.imdb.repos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.imdb.imdb.auth.PasswordChangeRequest;

// import java.util.Optional;


public interface PassReqRepository  extends MongoRepository<PasswordChangeRequest,ObjectId>{
}
