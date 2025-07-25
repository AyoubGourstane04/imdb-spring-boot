package com.imdb.imdb.auth;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {
    private String password;
    private String confirmPassword;
}
