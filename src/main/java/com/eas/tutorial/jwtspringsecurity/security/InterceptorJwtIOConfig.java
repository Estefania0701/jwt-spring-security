package com.eas.tutorial.jwtspringsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorJwtIOConfig implements WebMvcConfigurer {

    @Value("${eas.jwt.security.enabled:false}")
    private boolean securityEnabled;

    @Autowired
    private InterceptorJwtIO interceptorJwtIO;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // agrega un interceptor al registro de interceptores si la seguridad está habilitada

        if (securityEnabled) {
            registry.addInterceptor(interceptorJwtIO);
        }
    }
}
