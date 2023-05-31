package com.eas.tutorial.jwtspringsecurity.service;

import com.eas.tutorial.jwtspringsecurity.dto.JwtResponseDTO;
import com.eas.tutorial.jwtspringsecurity.dto.UserDTO;
import com.eas.tutorial.jwtspringsecurity.model.User;
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

    @Autowired
    private UserService userService;

    @Value("${eas.jwt.token.expired-in}")
    private int EXPIRED_IN;

    public JwtResponseDTO login(String clientId, String clientSecret, String username){
        // devuelve un token

        User userEntity = userService.getUserByUsername(username);

        UserDTO user = new UserDTO(userEntity);

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
