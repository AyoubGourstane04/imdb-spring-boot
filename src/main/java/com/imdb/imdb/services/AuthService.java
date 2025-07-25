package com.imdb.imdb.services;



import com.imdb.imdb.Role;
import com.imdb.imdb.User;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imdb.imdb.auth.AuthenticationRequest;
import com.imdb.imdb.auth.AuthenticationResponse;
import com.imdb.imdb.auth.PasswordChangeRequest;
import com.imdb.imdb.auth.PasswordChangeResponse;
import com.imdb.imdb.auth.RegisterRequest;
import com.imdb.imdb.config.JwtService;
import com.imdb.imdb.repos.AuthRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .age(request.getAge())
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .passwordResetFlag(false)
                        .lastPasswordChangeTime(LocalDateTime.now())
                        .build();
        authRepository.save(user);
        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                    .token(jwt)
                                    .user(user)
                                    .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var auth = authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                    request.getPassword())
        );
        if (!auth.isAuthenticated())
            throw new IllegalArgumentException("invalid password");

        var user = (User) auth.getPrincipal();

        String message = null;

        if (user.getPasswordResetFlag()){
            message = "POST http://localhost:8080/users/auth/updatePassword/" + user.getId().toString();
        }
        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                    .token(jwt)
                                    .user(user)
                                    .passwordChangeRequest(message)
                                    .build();
    }

    public PasswordChangeResponse updatePassword(ObjectId id, PasswordChangeRequest request){

        User user = authRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id+" not found."));
        
        if(!request.getPassword().equals(request.getConfirmPassword()))
            throw new IllegalArgumentException("the password and the confirmation password don't match!");
        

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPasswordResetFlag(false);
        user.setLastPasswordChangeTime(LocalDateTime.now());

        authRepository.save(user);

        return PasswordChangeResponse.builder().user(user).build();  
    }

    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

    public void saveUsers(List<User> users){
        authRepository.saveAll(users);
    }



}
