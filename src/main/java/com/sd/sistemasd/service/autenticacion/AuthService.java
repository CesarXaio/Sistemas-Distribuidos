package com.sd.sistemasd.service.autenticacion;

import com.sd.sistemasd.config.JwtProvider;
import com.sd.sistemasd.dto.autorizacion.AuthRequest;
import com.sd.sistemasd.dto.autorizacion.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public AuthResponse authenticate(AuthRequest authRequest){
        try {
            this.authenticateSecurity(authRequest.getUsername(), authRequest.getPassword());
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = this.jwtProvider.generateToken(userDetails);
        return new AuthResponse(token);
    }

    private void authenticateSecurity(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            e.printStackTrace();
            throw  new Exception("Disable user. " + e.getMessage());
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad credentials. " + e.getMessage());
        }
    }
}
