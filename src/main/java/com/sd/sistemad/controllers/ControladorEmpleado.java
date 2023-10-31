package com.sd.sistemad.controllers;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.services.ServiceEmpleado;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/empleados")
public class ControladorEmpleado {
    @Autowired
    private ServiceEmpleado serviceEmpleado;

    @GetMapping
    public List<Empleado> getEmpleados() {
        return serviceEmpleado.getEmpleados();
    }

    @PostMapping
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return serviceEmpleado.guardarEmpleado(empleado);
    }

    @GetMapping("/{id}")
    public Optional<Empleado> getEmpleadoById(@PathVariable("id") Long id) {
        return serviceEmpleado.getById(id);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleadoById(@RequestBody Empleado request, @PathVariable("id") Long id) {
        Optional<Empleado> empleadoOptional = serviceEmpleado.getById(id);

        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            empleado.setNombre(request.getNombre());
            empleado.setApellido(request.getApellido());
            empleado.setTelefono(request.getTelefono());
            empleado.setHoraInicio(request.getHoraInicio());
            empleado.setHoraFin(request.getHoraFin());
            empleado.setTipoEmpleado(request.getTipoEmpleado());

            // Guardar el empleado actualizado en la base de datos
            return serviceEmpleado.guardarEmpleado(empleado);
        } else {
            // Manejo de error, por ejemplo, puedes lanzar una excepción o devolver un empleado especial para indicar que no se encontró el empleado.
            // En este caso, devolver null, pero puedes personalizarlo según tus necesidades.
            return null;
        }
    }


    @DeleteMapping("/{id}")
    public String eliminarById(@PathVariable("id") Long id) {
        boolean ok = serviceEmpleado.eliminarEmpleado(id);
        if (ok) {
            return "Empleado con id " + id + " eliminado";
        } else {
            return "Error, no se pudo eliminar el id " + id;
        }
    }

}


    /*
    @Autowired
    private final ServiceEmpleado serviceEmpleado;

    @Autowired
    public ControladorEmpleado(ServiceEmpleado serviceEmpleado) {
        this.serviceEmpleado = serviceEmpleado;
    }

    @GetMapping
    public List<Empleado> getEmpleados() {
        List<Empleado> empleados = serviceEmpleado.getEmpleados();
        return empleados;
    }

    @PostMapping
    public ResponseEntity<Object> registrarEmpleado(@RequestBody Empleado empleado) {
        ResponseEntity<Object> nuevoEmpleado = serviceEmpleado.newEmpleado(empleado);
        if (nuevoEmpleado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el empleado");
        }
    }

    /*
    @PutMapping
    public ResponseEntity <Object>updateEmpleado(@RequestBody Empleado empleados){
        return this.serviceEmpleado.newEmpleado(empleados);

    }*/
/*
    @PutMapping
    public ResponseEntity<Object> actualizarEmpleado(@PathVariable("id") Long id, @RequestBody Empleado empleado) {
        ResponseEntity<Object> empleadoActualizado = serviceEmpleado.updateEmpleado(id, empleado);
        if (empleadoActualizado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarEmpleado(@PathVariable("id") Long id) {
        boolean eliminado = serviceEmpleado.deleteEmpleado(id).hasBody();
        if (eliminado) {
            return ResponseEntity.status(HttpStatus.OK).body("Empleado eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
        }
    }*/

