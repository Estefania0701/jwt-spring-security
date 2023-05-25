package com.eas.tutorial.jwtspringsecurity.service;

import com.eas.tutorial.jwtspringsecurity.dto.JwtResponse;
import com.eas.tutorial.jwtspringsecurity.dto.UsuarioDTO;
import com.eas.tutorial.jwtspringsecurity.security.JwtIO;
import com.eas.tutorial.jwtspringsecurity.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private JwtIO jwtIO;

    @Autowired
    private DateUtil dateUtil;

    @Value("${eas.jwt.token.expired-in}")
    private int EXPIRED_IN;

    public JwtResponse login(String clientId, String clientSecret){
        // autentica el cliente y genera un JWT


        UsuarioDTO user = UsuarioDTO.builder()
                .name("Estefanía")
                .lastname("Aguas")
                .role("ADMIN")
                .country("Colombia")
                .id("1")
                .build();

        // objeto jwt
        JwtResponse jwt = JwtResponse.builder()
                .tokenType("bearer")
                .accessToken(jwtIO.generateToken(user))
                .issuedAt(dateUtil.getDateMillis()+"")
                .clientId(clientId)
                .expiresIn(EXPIRED_IN) //1 hora
                .build();

        return jwt;
    }
}
