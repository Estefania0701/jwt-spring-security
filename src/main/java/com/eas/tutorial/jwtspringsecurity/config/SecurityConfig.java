package com.eas.tutorial.jwtspringsecurity.config;

import com.eas.tutorial.jwtspringsecurity.security.JwtTokenProvider;
import com.eas.tutorial.jwtspringsecurity.security.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuración de la cadena de filtros de seguridad

        http
            .authorizeRequests()
                .antMatchers("/demo/oauth/cliente_credential/accesstoken").permitAll() // Ruta de acceso público para la autenticación
                .antMatchers("/demo/saludo").hasAuthority("ROLE_ADMIN") // Solo los usuarios con el rol "ROLE_ADMIN" pueden acceder
                .and()
            .csrf()
                .disable() // Deshabilitar la protección CSRF
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No crear sesiones ya que se utiliza autenticación basada en tokens
                .and()
            .addFilterBefore(jwtValidationFilter(), BasicAuthenticationFilter.class); // Agregar el filtro JwtValidationFilter antes del BasicAuthenticationFilter

        return http.build();
    }

    @Bean
    public JwtValidationFilter jwtValidationFilter() {
        /* Proporciona una instancia de JwtValidationFilter que esté correctamente
        configurada y lista para ser utilizada en la cadena de filtros de seguridad.*/
        return new JwtValidationFilter(jwtTokenProvider);
    }
}
