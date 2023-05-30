package com.eas.tutorial.jwtspringsecurity.validator;

import com.eas.tutorial.jwtspringsecurity.dto.UserDTO;
import com.eas.tutorial.jwtspringsecurity.exception.ApiUnauthorized;
import com.eas.tutorial.jwtspringsecurity.model.User;
import com.eas.tutorial.jwtspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;
import java.util.Optional;

@Component
public class AuthValidator {
    /* Validador para verificar la validez de los parámetros de
    autenticación en una solicitud.*/

    @Autowired
    private final UserRepository userRepository;

    public AuthValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    public void validate(MultiValueMap<String, String> paramMap, String grantType) throws ApiUnauthorized {
        //Valida los parámetros de autenticación. Lanza ApiUnauthorized si son inválidos

        if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)) {
            message("El campo grant_type es inválido");
        }

        String clientId = paramMap.getFirst("client_id");
        String clientSecret = paramMap.getFirst("client_secret");

        if (Objects.isNull(paramMap) || clientId.isEmpty() || clientSecret.isEmpty()) {
            message("client_id y/o client_secret son inválidos");
        }

        String username = paramMap.getFirst("username");
        String password = paramMap.getFirst("password");

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) {
            message("Usuario incorrecto");
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(password)) {
            message("Contraseña incorrecta");
        }
    }

    private void message(String message) throws ApiUnauthorized {
        // Lanza excepción de no autorizado

        throw new ApiUnauthorized(message);
    }
}
