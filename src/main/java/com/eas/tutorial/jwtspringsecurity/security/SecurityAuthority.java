package com.eas.tutorial.jwtspringsecurity.security;

import com.eas.tutorial.jwtspringsecurity.model.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    // Representa una autoridad o rol asignado a un usuario en el contexto de seguridad de la aplicación.

    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName().toString();
    }
}