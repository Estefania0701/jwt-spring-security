package com.eas.tutorial.jwtspringsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtValidationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // lleva a cabo la validación y autenticación del tokenn

        // obtengo el string token
        String token = extractToken(request);

        // validar y autenticar el token jwt
        if (token != null && !jwtTokenProvider.tokenIsExpired(token)) {
            // creo la autenticación
            Authentication authentication = jwtTokenProvider.createAuthentication(token);
            // establezco la autenticación en el contexto de seguridad actual
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    public String extractToken(HttpServletRequest request) {
        // extrae el token del encabezado de la solicitud

        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            return bearerToken.substring(AUTHORIZATION_HEADER_PREFIX.length());
        }
        return null;
    }
}
