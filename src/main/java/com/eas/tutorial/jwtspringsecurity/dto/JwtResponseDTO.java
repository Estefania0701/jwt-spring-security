package com.eas.tutorial.jwtspringsecurity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data // Genera automáticamente los métodos equals(), hashCode(), toString(), así como los métodos de acceso (getters) y mutación (setters) para los campos de la clase
public class JwtResponseDTO {
    // Contiene la información del token que se envía como respuesta al cliente

    // ---- datos fundamentales
    @JsonProperty(value="token_type")
    private String tokenType; //necesario en la response

    @JsonProperty(value="access_token")
    private String accessToken; //necesario en la response

    @JsonProperty(value="expires_in")
    private int expiresIn; //necesario en la response

    // ---- datos personalizados
    @JsonProperty(value="issued_id")
    private String issuedAt; // en qué momento se solicitó el token

    @JsonProperty(value="client_id")
    private String clientId; // recibido por parámetro, y si sale bien la autenticación se devuelve
}
