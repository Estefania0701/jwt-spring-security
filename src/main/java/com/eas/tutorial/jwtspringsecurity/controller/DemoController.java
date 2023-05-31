package com.eas.tutorial.jwtspringsecurity.controller;

import com.eas.tutorial.jwtspringsecurity.dto.UserDTO;
import com.eas.tutorial.jwtspringsecurity.model.User;
import com.eas.tutorial.jwtspringsecurity.security.JwtTokenProvider;
import com.eas.tutorial.jwtspringsecurity.security.JwtValidationFilter;
import com.eas.tutorial.jwtspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    JwtValidationFilter jwtValidationFilter;

    @GetMapping("/saludo")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok("Hola mundo");
    }

    // busca un usuario por su id
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id, HttpServletRequest request) {

        // extrae el token de la solicitud
        String tokenAuthenticatedUser = jwtValidationFilter.extractToken(request);

        // depura la cadena del token (sin bearer)
        String idAuthenticatedUser = jwtTokenProvider.getSubFromToken(tokenAuthenticatedUser);

        // verifica si el sub (id) del token coincide con el id consultado en la URL
        if(id.toString().equals(idAuthenticatedUser)) {
            return ResponseEntity.ok(userService.getUserById(id));
        }

        // extrae los roles del token
        List<String> rolesAuthenticatedUser = jwtTokenProvider.getRoleFromToken(tokenAuthenticatedUser);

        // recorre los roles del usuario
        for (String role : rolesAuthenticatedUser) {
            // si el role es de admin
            if (role.equals("ROLE_ADMIN")) {
                return ResponseEntity.ok(userService.getUserById(id));
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // busca usuarios con base en filtros
    @GetMapping("usuarios")
    public ResponseEntity<List<UserDTO>> searchUsers(
            @RequestParam(name = "country", required = false) String country) {
        return ResponseEntity.ok(userService.searchUsers(country));
    }
}
