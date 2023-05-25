package com.eas.tutorial.jwtspringsecurity.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class InterceptorJwtIO implements HandlerInterceptor {
    /* Un interceptor es un componente que permite interceptar y procesar
    las solicitudes HTTP antes de que lleguen a los controladores de la
    aplicación. En este caso, el interceptor se utiliza para realizar
    validaciones antes de que se maneje una solicitud.*/

    @Value("${eas.jwt.token.auth.path}")
    private String AUTH_PATH;

    // rutas excluidas
    // convierto a una lista las rutas excluidas
    @Value("#{'${eas.jwt.excluded.path}'.split(',')}")
    private List<String> excluded;

    @Autowired
    private JwtIO jwtIO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* Se ejecuta antes de que se maneje la solicitud y permite
        realizar acciones de validación o procesamiento previo.*/

        boolean validate = false;
        String uri = request.getRequestURI();

        // ----------- revisar la ruta
        // si la ruta es de login o si es una ruta excluida
        if (uri.equals(AUTH_PATH) || excluded(uri)) {
            validate = true;
        }

        // ----------- revisar la expiración del token
        if (!validate && request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty()) {

            // obtengo el token solito sin "Bearer"
            String token = request.getHeader("Authorization").replace("Bearer", "");

            // valido si el token ya expiró (true) y convierto a false con ! para invalidarlo
            validate = !jwtIO.validateToken(token);
        }

        // ----------- enviar mensaje 401 de no autorizado
        if (!validate) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401
        }

        return validate;
    }

    private boolean excluded(String path) {
        // verifica si una ruta está en la lista de rutas excluidas

        boolean result = false;

        for (String exc : excluded) {
            if (exc.equals("#") && exc.equals(path)) {
                result = true;
            }
        }

        return result;
    }
}
