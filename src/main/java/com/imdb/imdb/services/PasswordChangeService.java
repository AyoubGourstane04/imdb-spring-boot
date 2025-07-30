package com.imdb.imdb.services;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imdb.imdb.auth.PasswordChangeRequest;
import com.imdb.imdb.repos.PassReqRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Data
public class PasswordChangeService {
    private final PassReqRepository passReqRepository;


    public PasswordChangeRequest createPasswordChangeRequest(ObjectId id, PasswordChangeRequest request){
        PasswordChangeRequest req = passReqRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  id.toString() +" not found."));
        req.setUserId(request.getUserId());
        req.setPassword(request.getPassword());
        req.setConfirmPassword(request.getConfirmPassword());
        
        return passReqRepository.save(req);
    }


    public PasswordChangeRequest createPasswordChangeRequestByUserId(String user_id){
        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setUserId(user_id);
        request.setPassword(null);
        request.setConfirmPassword(null);

        return passReqRepository.save(request);

    }

    public PasswordChangeRequest getPasswordChangeRequestsById(ObjectId id){
        return passReqRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id.toString() +" not found."));
    }


}
