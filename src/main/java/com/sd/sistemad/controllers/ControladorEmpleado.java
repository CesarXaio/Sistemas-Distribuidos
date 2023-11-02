package com.sd.sistemad.controllers;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.services.ServiceEmpleado;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping(path = "/empleados")
public class ControladorEmpleado {
    private static final Logger logger = LoggerFactory.getLogger(ControladorEmpleado.class);

    @Autowired
    private ServiceEmpleado serviceEmpleado;

    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        try {
            List<Empleado> empleados = serviceEmpleado.getEmpleados();
            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            logger.error("Error al obtener empleados: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoGuardado = serviceEmpleado.guardarEmpleado(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);
        } catch (Exception e) {
            logger.error("Error al guardar empleado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("id") Long id) {
        try {
            Optional<Empleado> empleadoOptional = serviceEmpleado.getById(id);
            if (empleadoOptional.isPresent()) {
                return ResponseEntity.ok(empleadoOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error al obtener empleado por ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleadoById(@RequestBody Empleado request, @PathVariable("id") Long id) {
        try {
            Optional<Empleado> empleadoOptional = serviceEmpleado.getById(id);

            if (empleadoOptional.isPresent()) {
                Empleado empleado = empleadoOptional.get();
                // Tu lógica para actualizar el empleado
                Empleado empleadoActualizado = serviceEmpleado.guardarEmpleado(empleado);
                return ResponseEntity.ok(empleadoActualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error al actualizar empleado por ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarById(@PathVariable("id") Long id) {
        try {
            boolean ok = serviceEmpleado.eliminarEmpleado(id);
            if (ok) {
                return ResponseEntity.ok("Empleado con id " + id + " eliminado");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, no se pudo eliminar el id " + id);
            }
        } catch (Exception e) {
            logger.error("Error al eliminar empleado por ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el empleado.");
        }
    }
}


