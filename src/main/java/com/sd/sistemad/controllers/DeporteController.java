package com.sd.sistemad.controllers;

import com.sd.sistemad.models.Deporte;
import com.sd.sistemad.services.DeporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deportes")
public class DeporteController {
    @Autowired
    private DeporteService deporteService;

    @PostMapping("/nuevo")
    public ResponseEntity<String> guardarNuevoDeporte(@RequestBody Deporte request) {
        try {
            deporteService.guardarDeporte(request.getNombre());
            return ResponseEntity.ok("Deporte guardado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el deporte: " + e.getMessage());
        }
    }
}

