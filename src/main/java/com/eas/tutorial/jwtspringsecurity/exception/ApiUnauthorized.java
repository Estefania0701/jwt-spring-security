package com.eas.tutorial.jwtspringsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) //401
public class ApiUnauthorized extends Exception {
    /* cuando se lanza esta excepción, se devuelve una respuesta HTTP
    con el código de estado 401 Unauthorized.*/

    // NECESARIO??
    //private static final Long serialVersionUID = 5071496619546474611L;

    public ApiUnauthorized(String message) {
        super(message);
    }
}
