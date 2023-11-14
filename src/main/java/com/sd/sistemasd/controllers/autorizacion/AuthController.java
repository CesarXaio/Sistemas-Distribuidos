package com.sd.sistemasd.controllers.autorizacion;

import com.sd.sistemasd.dto.autorizacion.AuthRequest;
import com.sd.sistemasd.service.autenticacion.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    private ResponseEntity<?> authenticate (@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
