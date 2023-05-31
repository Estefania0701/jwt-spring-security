package com.eas.tutorial.jwtspringsecurity.dto;

import com.eas.tutorial.jwtspringsecurity.model.Authority;
import com.eas.tutorial.jwtspringsecurity.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = (user.getAuthorities() != null) ? createListAuthorities(user.getAuthorities()) : null;
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.country = user.getCountry();
    }

    private Long id;
    private String username;
    private String password;
    private List<String> authorities;
    private String name;
    private String lastname;
    private String country;

    private List<String> createListAuthorities(List<Authority> authorities) {

        List<String> authoritiesString = new ArrayList<>();

        for (Authority a : authorities) {
            authoritiesString.add(a.getName().toString());
        }

        return authoritiesString;
    }

}
