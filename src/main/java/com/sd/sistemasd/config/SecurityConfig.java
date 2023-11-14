package com.sd.sistemasd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private  JwtAuthenticationFilter authenticationfilter;
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //configuracion que permite filtrar las solicitudes que requieren autenticacion

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf( csrf -> csrf.disable() )
                .cors( Customizer.withDefaults() )
                .authorizeHttpRequests(  authorize -> authorize
                        .requestMatchers(
                                "/api/auth/**",
                                "/user/create"
                        ).permitAll()

                        .requestMatchers(HttpMethod.OPTIONS).permitAll()

                        .anyRequest().authenticated())

                .exceptionHandling(exc -> exc.authenticationEntryPoint(unauthorizedHandler))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(authenticationfilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
