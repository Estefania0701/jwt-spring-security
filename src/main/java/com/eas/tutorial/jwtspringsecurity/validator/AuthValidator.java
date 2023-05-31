package com.eas.tutorial.jwtspringsecurity.validator;

import com.eas.tutorial.jwtspringsecurity.exception.ApiUnauthorized;
import com.eas.tutorial.jwtspringsecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {
    /* Validador para verificar la validez de los parámetros de
    autenticación en una solicitud.*/

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    public void validate(MultiValueMap<String, String> paramMap, String grantType) throws ApiUnauthorized {
        //Valida los parámetros de autenticación. Lanza ApiUnauthorized si son inválidos

        validateClient(paramMap, grantType);
        validateUser(paramMap);
    }

    private void message(String message) throws ApiUnauthorized {
        // Lanza excepción de no autorizado

        throw new ApiUnauthorized(message);
    }

    private void validateClient(MultiValueMap<String, String> paramMap, String grantType) throws ApiUnauthorized {
        // valida las credenciales del cliente

        if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)) {
            message("El campo grant_type es inválido");
        }

        String clientId = paramMap.getFirst("client_id");
        String clientSecret = paramMap.getFirst("client_secret");

        if (Objects.isNull(paramMap) || clientId.isEmpty() || clientSecret.isEmpty()) {
            message("client_id y/o client_secret son inválidos");
        }
    }

    private void validateUser(MultiValueMap<String, String> paramMap) throws ApiUnauthorized {
        // valida las credenciales del usuario

        String username = paramMap.getFirst("username");
        String password = paramMap.getFirst("password");

        // si no se ingresan las credenciales
        if (username == null || password == null) {
            throw new ApiUnauthorized("Credenciales de usuario inválidas");
        }

        // si las credenciales son incorrectas
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ApiUnauthorized("Credenciales de usuario inválidas");
        }
    }
}
