package com.unifor.estuda_facil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF (para APIs e H2 Console)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Todas as rotas são públicas!
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()) // Permite H2 Console em frames
                )
                .formLogin(form -> form.disable()) // Desabilita login por formulário
                .httpBasic(basic -> basic.disable()); // Desabilita autenticação básica

        return http.build();
    }
}