package com.eas.tutorial.jwtspringsecurity.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private final Object principal; // el objeto principal o la identidad del usuario
    private final List<GrantedAuthority> authorities; // roles asociados a principal

    public JwtAuthentication(Object principal, List<GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.authorities = authorities;
        setAuthenticated(true); // Indica que la autenticación es exitosa
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
