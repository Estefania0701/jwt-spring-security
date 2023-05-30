package com.eas.tutorial.jwtspringsecurity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Data
@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private String name;
    private String lastname;
    private String username;
    private String role;
    private String country;
}
