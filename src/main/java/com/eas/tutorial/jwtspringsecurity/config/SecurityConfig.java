package com.eas.tutorial.jwtspringsecurity.config;

import com.eas.tutorial.jwtspringsecurity.security.JwtTokenProvider;
import com.eas.tutorial.jwtspringsecurity.security.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuración de la cadena de filtros de seguridad

        http
            .authorizeRequests()
                .antMatchers("/demo/oauth/cliente_credential/accesstoken").permitAll()
                .antMatchers("/demo/saludo").hasAuthority("ROLE_USER")
                .antMatchers("/demo/usuarios").hasAuthority("ROLE_ADMIN")
                .antMatchers("/demo/usuarios/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
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
        return new JwtValidationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Codifica y verifica contraseñas
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
