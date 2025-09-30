package com.imdb.imdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
    private static final String[] PATTERNS = {
                                                    "/users/admin/**",
                                                "/users/auth/**","/users/files/**","/templates/**",
                                                "/static/**","/js/**","/icons/**"
                                            };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                    request -> {request
                                    .requestMatchers(PATTERNS[1]).permitAll()
                                    .requestMatchers(PATTERNS[2]).permitAll()
                                    .requestMatchers(PATTERNS[3]).permitAll()
                                    .requestMatchers(PATTERNS[4]).permitAll()
                                    .requestMatchers(PATTERNS[5]).permitAll()
                                    .requestMatchers(PATTERNS[6]).permitAll()
                                    .requestMatchers(PATTERNS[0])
                                    .hasRole("ADMIN")
                                    .anyRequest().authenticated();
                                }   
                ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
