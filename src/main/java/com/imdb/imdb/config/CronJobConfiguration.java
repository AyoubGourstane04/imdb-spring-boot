package com.imdb.imdb.config;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.imdb.imdb.User;
import com.imdb.imdb.services.AuthService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Configuration 
@Data
@RequiredArgsConstructor
public class CronJobConfiguration {

    private final AuthService authService ;

    @Scheduled(cron = " * 10 * * * * ")
    public void checkPasswordAge(){
        List<User> users = authService.getAllUsers()
                    .stream()
                    .filter(user -> Duration.between(user.getLastPasswordChangeTime(),LocalDateTime.now()).getSeconds() > 30)
                    .peek(user -> user.setPasswordResetFlag(true))
                    .collect(Collectors.toList());

        authService.saveUsers(users);
        
    }




}
