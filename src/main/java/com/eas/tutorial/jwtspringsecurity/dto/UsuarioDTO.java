package com.eas.tutorial.jwtspringsecurity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class UsuarioDTO implements Serializable {

    private String id;
    private String name;
    private String lastname;
    private String role;
    private String country;
}
