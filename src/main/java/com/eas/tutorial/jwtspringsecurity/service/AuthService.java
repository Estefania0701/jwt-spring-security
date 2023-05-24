package com.eas.tutorial.jwtspringsecurity.service;

import com.eas.tutorial.jwtspringsecurity.dto.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public JwtResponse login(String clientId, String clientSecret){

        // objeto jwt
        JwtResponse jwt = JwtResponse.builder()
                .tokenType("bearer")
                .accessToken("asfdghtsegdfvstrs")
                .issuedAt(System.currentTimeMillis()+"")
                .clientId(clientId)
                .expiresIn(3600) //1 hora
                .build();

        return jwt;
    }
}
