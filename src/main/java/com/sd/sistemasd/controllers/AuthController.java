package com.sd.sistemasd.controllers;

import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import com.sd.sistemasd.dao.empleado.EmpleadoDAO;
import com.sd.sistemasd.dao.role.RoleDao;
import com.sd.sistemasd.security.AuthResponse;
import com.sd.sistemasd.security.jwt.JwtTokenUtil;
import com.sd.sistemasd.service.user.UserService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sd.sistemasd.security.AuthRequest;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmpleadoDAO empleadoDAO;
    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            final Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            final EmpleadoBean user = (EmpleadoBean) authentication.getPrincipal();
            final String accessToken = jwtUtil.generateAccesToken(user);

            Set<String> roles = userService.getUserRoles(user.getEmail());

            final AuthResponse response = new AuthResponse(user.getEmail(), accessToken, roles);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
