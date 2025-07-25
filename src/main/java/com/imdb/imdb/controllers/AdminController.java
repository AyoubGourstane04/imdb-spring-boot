package com.imdb.imdb.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.imdb.User;
import com.imdb.imdb.auth.AuthenticationResponse;
import com.imdb.imdb.auth.RegisterRequest;
import com.imdb.imdb.services.AuthService;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@EnableMethodSecurity
@RequiredArgsConstructor
@RestController
@RequestMapping("users/admin")
public class AdminController {
    
    private final AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return authService.getAllUsers();
    }
}
