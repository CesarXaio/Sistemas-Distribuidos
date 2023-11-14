package com.sd.sistemasd.controllers.autorizacion;

import com.sd.sistemasd.entity.UserEntity;
import com.sd.sistemasd.service.autenticacion.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){return ResponseEntity.ok(userService.findAll());}

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody UserEntity entity){
        return ResponseEntity.ok(userService.save(entity));
    }
}
