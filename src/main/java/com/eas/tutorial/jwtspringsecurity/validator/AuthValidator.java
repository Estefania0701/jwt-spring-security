package com.eas.tutorial.jwtspringsecurity.validator;

import com.eas.tutorial.jwtspringsecurity.exception.ApiUnauthorized;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {
    /* Validador para verificar la validez de los parámetros de
    autenticación en una solicitud.*/

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    public void validate(MultiValueMap<String, String> paramMap, String grantType) throws ApiUnauthorized {
        //Valida los parámetros de autenticación. Lanza ApiUnauthorized si son inválidos

        if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)) {
            message("El campo grant_type es inválido");
        }

        if(Objects.isNull(paramMap) ||
                paramMap.getFirst("client_id").isEmpty() ||
                paramMap.getFirst("client_secret").isEmpty()) {
            message("client_id y/o client_secret son inválidos");
        }
    }

    private void message(String message) throws ApiUnauthorized {
        // Lanza excepción de no autorizado

        throw new ApiUnauthorized(message);
    }
}
