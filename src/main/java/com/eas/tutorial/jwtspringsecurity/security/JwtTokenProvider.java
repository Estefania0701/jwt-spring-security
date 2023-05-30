package com.eas.tutorial.jwtspringsecurity.security;

import com.eas.tutorial.jwtspringsecurity.dto.UsuarioDTO;
import com.eas.tutorial.jwtspringsecurity.util.UUIDGenerator;
import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // clave secreta
    @Value("${eas.jwt.token.secret:secret}") //lo que hay luego de los : es para asignar valores por defecto
    private String SECRET;

    @Value("${eas.jwt.timezone:UTC}")
    private String TIMEZONE;

    @Value("${eas.jwt.token.expired-in:3600}")
    private int EXPIRED_IN;

    @Value("${eas.jwt.issuer:none}")
    private String ISSUER;

    public String generateToken(UsuarioDTO usuario) {
        // crea el string del token y lo retorna

        String sub = UUIDGenerator.generateUUID(); // identificador único de sesión

        //String subject = GsonUtil.serialize(src);

        // construcción de HMAC signer usando SHA-256
        Signer signer = HMACSigner.newSHA256Signer(SECRET);

        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(timeZone.toZoneId()).plusSeconds(EXPIRED_IN);

        JWT jwt = new JWT()
                .setIssuer(ISSUER)
                .setIssuedAt(ZonedDateTime.now(timeZone.toZoneId()))
                .setSubject(sub)
                .addClaim("name", usuario.getName())
                .addClaim("lastname", usuario.getLastname())
                .addClaim("username", usuario.getUsername())
                .addClaim("role", usuario.getRole())
                .addClaim("country", usuario.getCountry())
                .setExpiration(zonedDateTime);

        return JWT.getEncoder().encode(jwt, signer);
    }

    public boolean tokenIsExpired(String encodedJWT) {
        // valida si el token ya expiró

        JWT jwt = decodeTokenPayload(encodedJWT);
        return jwt.isExpired();
    }

    private JWT decodeTokenPayload(String encodedJWT) {
        // decodifica el payload del token

        return JWTUtils.decodePayload(encodedJWT);
    }

    private Map<String, Object> getPayload(String encodedJWT) {
        // Obtiene el payload

        JWT jwt = decodeTokenPayload(encodedJWT);
        return jwt.getAllClaims();
    }

    private String getSubFromToken(String encodedJwt) {
        // Devuelve el valor sub del payload

        Map<String, Object> claims = getPayload(encodedJwt);
        String sub = (String) claims.get("sub");

        return sub;
    }

    private List<String> getRoleFromToken(String encodedJwt) {

        Map<String, Object> claims = getPayload(encodedJwt);
        Object roleObj = claims.get("role");

        // verifica si el valor del campo "role" es una lista
        if (roleObj instanceof List<?>) {
            List<?> roles = (List<?>) roleObj;
            // convierte los elementos de la lista en cadenas y devuelve la lista de roles
            return roles.stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        } else if (roleObj != null) {
            // si el valor no es una lista, lo convierte en una sola cadena y devuelve una lista con un solo elemento
            return Collections.singletonList(roleObj.toString());
        }

        // si no se encuentra el campo "role" o es nulo, devuelve una lista vacía
        return Collections.emptyList();
    }

    public Authentication createAuthentication(String encodedJwt) {

        // obtengo el sub (uuid)
        String subject = getSubFromToken(encodedJwt);

        // obtengo el rol del JWT
        List<String> roles = getRoleFromToken(encodedJwt);

        // Crea una lista de GrantedAuthority a partir de los roles
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        JwtAuthentication jwtAuthentication = new JwtAuthentication(subject, authorities);

        return jwtAuthentication;
    }
}
