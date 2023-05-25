package com.eas.tutorial.jwtspringsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class JwtController {

    // ruta no protegida
    @GetMapping("/saludo")
    public ResponseEntity<Object> saludar() {
        return ResponseEntity.ok("Hola mundo");
    }
}
