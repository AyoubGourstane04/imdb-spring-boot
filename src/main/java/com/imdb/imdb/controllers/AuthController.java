package com.imdb.imdb.controllers;



import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.imdb.auth.AuthenticationRequest;
import com.imdb.imdb.auth.AuthenticationResponse;
import com.imdb.imdb.auth.PasswordChangeRequest;
import com.imdb.imdb.auth.PasswordChangeResponse;
import com.imdb.imdb.services.AuthService;



@RestController
@RequestMapping("users/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/updatePassword/{id}")
    public ResponseEntity<PasswordChangeResponse> updatePassword(@PathVariable ObjectId id, @RequestBody PasswordChangeRequest request){
            return ResponseEntity.ok(authService.updatePassword(id,request));
    }

    /*
     * 
     *      write a cron job that iterate over the users 
     *      and check if the password is old  and update a VARIABLE IN the user entity 
     *        when the user tries to login  before returing the token 
     *      if (password.isOld())
     *           createAChangepasswordRequset with the target user Id and some other info 
     *           then redirect the user to the change password page with the changePasswordRequest.Id 
     * 
     * @param
     * @return
     */


    



                                                                
}
