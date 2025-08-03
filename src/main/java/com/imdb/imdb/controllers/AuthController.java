package com.imdb.imdb.controllers;




import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.imdb.User;
import com.imdb.imdb.auth.AuthenticationRequest;
import com.imdb.imdb.auth.AuthenticationResponse;
import com.imdb.imdb.auth.PasswordChangeRequest;
import com.imdb.imdb.auth.PasswordChangeResponse;
import com.imdb.imdb.services.AuthService;
import com.imdb.imdb.services.PasswordChangeService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;



@CrossOrigin(origins = {"http://127.0.0.1:8080", "http://localhost:8080"}, maxAge = 3600)
@RestController
@RequestMapping("users/auth")
@Data
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordChangeService passwordChangeService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){ 

        AuthenticationResponse response = authService.authenticate(request);
        
        if(response.getPasswordChangeFlag()){
            User user = authService.getUserByUsername(request.getUsername());
            var req = passwordChangeService.createPasswordChangeRequestByUserId(user.getId().toString());
          
            return ResponseEntity.status(302)
                                 .header("Location","/static/updatePassword/"+ req.getId())
                                 .build();
        }
            return ResponseEntity.ok(response);
    }


    @PostMapping("/updatePassword/{id}")
    public ResponseEntity<PasswordChangeResponse> updatePassword(@PathVariable ObjectId id, @RequestBody PasswordChangeRequest body){
        PasswordChangeRequest request = passwordChangeService.getPasswordChangeRequestsById(id);
            
        request.setPassword(body.getPassword());
        request.setConfirmPassword(body.getConfirmPassword());
            
        var req = passwordChangeService.createPasswordChangeRequest(id,request); 

       return ResponseEntity.ok(authService.updatePassword(req));
    }

    @GetMapping("/me")
    public User getUserByToken(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
    


                                                                
}
