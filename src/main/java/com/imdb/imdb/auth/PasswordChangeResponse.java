package com.imdb.imdb.auth;

import com.imdb.imdb.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeResponse {
    private User user;
}
