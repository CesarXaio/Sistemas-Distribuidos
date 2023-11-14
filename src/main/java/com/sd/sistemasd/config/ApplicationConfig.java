package com.sd.sistemasd.config;

import com.sd.sistemasd.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Autowired
    private UserDAO userDAO;

    @Bean
    public UserDetailsService userDetailsService(){
        return (username)->{
            return userDAO.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        };
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
}
