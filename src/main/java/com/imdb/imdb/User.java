package com.imdb.imdb;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails{
    @Id
    private ObjectId id;
    private String firstname;
    private String lastname;
    private Integer age;  
    private String username;
    @JsonIgnore
    private String password;
    private Role role;
    private Boolean passwordResetFlag;
    private LocalDateTime lastPasswordChangeTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
    }


    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

    @Override
	public boolean isAccountNonLocked() {
		return true;
	}

    @Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

    @Override
	public boolean isEnabled() {
		return true;
	}

}
