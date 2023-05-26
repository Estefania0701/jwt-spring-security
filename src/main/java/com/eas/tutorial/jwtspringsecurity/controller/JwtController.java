package com.eas.tutorial.jwtspringsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class JwtController {

    @GetMapping("/saludo")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok("Hola mundo");
    }
}
