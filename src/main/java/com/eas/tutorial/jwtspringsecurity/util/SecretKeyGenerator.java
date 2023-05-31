package com.eas.tutorial.jwtspringsecurity.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SecretKeyGenerator {

    public static String generateSecretKey() {
        // Crear una instancia de SecureRandom
        SecureRandom secureRandom = new SecureRandom();

        // Generar una clave secreta de 32 bytes
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);

        // Codificar la clave secreta en Base64
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);

        return secretKey;
    }
}
