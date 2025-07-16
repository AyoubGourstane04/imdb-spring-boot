package com.imdb.imdb.services;



import com.imdb.imdb.Role;
import com.imdb.imdb.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.imdb.imdb.auth.AuthenticationRequest;
import com.imdb.imdb.auth.AuthenticationResponse;
import com.imdb.imdb.auth.ResgisterRequest;
import com.imdb.imdb.config.JwtService;
import com.imdb.imdb.repos.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


   
    public AuthenticationResponse register(ResgisterRequest request){
        var user = User.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .age(request.getAge())
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .build();
        authRepository.save(user);
        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                    .token(jwt)
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
        // var user  = authRepository.findByUsername(request.getUsername())
        //                             .orElseThrow(() -> new UsernameNotFoundException(request.getUsername() +" not found"));
        // if (user.getPassword()!= passwordEncoder.encode(request.getPassword()))
        //     throw new IllegalArgumentException("invalid password");
        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                    .token(jwt)
                                    .user(user)
                                    .build();
    }
}
