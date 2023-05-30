package com.eas.tutorial.jwtspringsecurity.service;

import com.eas.tutorial.jwtspringsecurity.dto.JwtResponseDTO;
import com.eas.tutorial.jwtspringsecurity.dto.UsuarioDTO;
import com.eas.tutorial.jwtspringsecurity.security.JwtTokenProvider;
import com.eas.tutorial.jwtspringsecurity.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private DateUtil dateUtil;

    @Value("${eas.jwt.token.expired-in}")
    private int EXPIRED_IN;

    public JwtResponseDTO login(String clientId, String clientSecret){
        // devuelve un token

        UsuarioDTO user = UsuarioDTO.builder()
                .name("Estefanía")
                .lastname("Aguas")
                .username("estefaaguas04")
                .role("ROLE_ADMIN")
                .country("Colombia")
                .build();

        // objeto jwt
        JwtResponseDTO jwt = JwtResponseDTO.builder()
                .tokenType("bearer")
                .accessToken(jwtTokenProvider.generateToken(user))
                .issuedAt(dateUtil.getDateMillis()+"")
                .clientId(clientId)
                .expiresIn(EXPIRED_IN)
                .build();
        return jwt;
    }
}
