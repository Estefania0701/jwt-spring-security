package com.eas.tutorial.jwtspringsecurity.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class AuthController {

    // "/demo/oauth/cliente_credential/accesstoken"
    /*
    --- consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE: Esta anotación
    especifica el tipo de medios que el método puede consumir. En este caso,
    el método espera datos en formato application/x-www-form-urlencoded.
    (clave1=valor1&clave2=valor2&clave3=valor3...)
    --- produces = MediaType.APPLICATION_JSON_VALUE: Esta anotación especifica
    el tipo de medios que el método puede producir. En este caso, el método
    generará una respuesta en formato JSON.
    {
    "clave1": "valor1",
    "clave2": "valor2",
    "clave3": "valor3"
    }
    --- Toma dos parámetros: paramMap, que representa los datos enviados en
    el cuerpo de la solicitud y se espera que sea un MultiValueMap que
    contenga pares clave-valor, y grantType, que se espera que sea un
    parámetro de consulta con el nombre "grant_type" y un valor correspondiente.
    */
    @PostMapping(value = "oauth/cliente_credential/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> paramMap, @RequestParam("grant_type") String grantType) {
        return ResponseEntity.ok().build();
    }
}
