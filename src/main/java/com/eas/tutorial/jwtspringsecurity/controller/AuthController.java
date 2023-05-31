package com.eas.tutorial.jwtspringsecurity.controller;

import com.eas.tutorial.jwtspringsecurity.exception.ApiUnauthorized;
import com.eas.tutorial.jwtspringsecurity.service.AuthService;
import com.eas.tutorial.jwtspringsecurity.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthValidator validator;

    // paramMap representa los datos enviados en el cuerpo de la solicitud (formulario)
    // grantType para el método de autenticación y autorización
    @PostMapping(value = "oauth/cliente_credential/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> paramMap, @RequestParam("grant_type") String grantType) throws ApiUnauthorized {

        // valida los parámetros recibidos y lanza excepción si falla
        validator.validate(paramMap, grantType);

        // llama al servicio para generar un token
        return ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret"), paramMap.getFirst("username")));
    }
}
