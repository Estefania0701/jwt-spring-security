package com.eas.tutorial.jwtspringsecurity.security;

import com.eas.tutorial.jwtspringsecurity.util.GsonUtil;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.TimeZone;

@Component
public class JwtIO {

    // clave secreta
    @Value("${eas.jwt.token.secret:secret}") //lo que hay luego de los : es para asignar valores por defecto
    private String SECRET;

    @Value("${eas.jwt.timezone:UTC}")
    private String TIMEZONE;

    @Value("${eas.jwt.token.expired-in:3600}")
    private int EXPIRED_IN;

    @Value("${eas.jwt.issuer:none}")
    private String ISSUER;

    public String generateToken(Object src) {
        // crea el string del token y lo retorna

        String subject = GsonUtil.serialize(src);

        // construcción de HMAC signer usando SHA-256
        Signer signer = HMACSigner.newSHA256Signer(SECRET);

        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(timeZone.toZoneId()).plusSeconds(EXPIRED_IN);

        JWT jwt = new JWT()
                .setIssuer(ISSUER)
                .setIssuedAt(ZonedDateTime.now(timeZone.toZoneId()))
                .setSubject(subject)
                .setExpiration(zonedDateTime);

        return JWT.getEncoder().encode(jwt, signer);
    }

    public boolean validateToken(String encodedJWT) {
        return false;
    }

    public String getPayload(String encodedJWT) {
        return null;
    }

    private JWT jwt(String encodedJWT) {
        // codifica el string token

        // para verificar el token
        Verifier verifier = HMACVerifier.newVerifier(SECRET);

        // retorna el string token en el formato JWT que necesito
        return JWT.getDecoder().decode(encodedJWT, verifier);
    }
}
