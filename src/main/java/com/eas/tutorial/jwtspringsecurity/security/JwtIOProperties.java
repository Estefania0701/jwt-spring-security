package com.eas.tutorial.jwtspringsecurity.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="eas.jwt")
public class JwtIOProperties {

    private Security security;

    private String timezone;

    private String issuer;

    private Token token;

    private Excluded excluded;

    @Data
    public static class Security {
        // para controlar si la seguridad estará activa o inactiva en la aplicación
        private boolean enabled;
    }

    @Data
    public static class Token {
        private Auth auth;
        private String secret;
        private int expiredIn;
    }

    @Data
    public static class Auth {
        private String path;
    }

    @Data
    public static class Excluded {
        private String path;
    }
}
