package com.eas.tutorial.jwtspringsecurity.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="eas.jwt")
public class JwtIOProperties {

    // activar o desactivar la seguridad
    private Security security;

    // zona horaria
    private String timezone;

    // entidad que emite el token (emisor)
    private String issuer;

    // claims registradas del token
    private Token token;

    // rutas exluidas del sistema de seguridad
    private Excluded excluded;

    @Data
    public static class Security {
        private boolean enabled; // para activar o desactivar la seguridad de la app
    }

    @Data
    public static class Token {
        private Auth auth; // endpoint para la autenticación
        private String secret; // clave secreta
        private int expiredIn; // tiempo de expiración (segundos)
    }

    @Data
    public static class Auth {
        private String path; // endpoint para la autenticación
    }

    @Data
    public static class Excluded {
        // para las rutas que he decidido que no requerirán autenticación
        // no serán tenidas en cuenta por el sistema de seguridad
        private String path;
    }
}
