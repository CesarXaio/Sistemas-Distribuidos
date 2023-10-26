package com.sd.sistemad.controllers;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.services.ServiceEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "Empleados")
public class ControladorEmpleado {
    private final ServiceEmpleado serviceEmpleado;

    @Autowired
    public ControladorEmpleado(ServiceEmpleado serviceEmpleado){
        this.serviceEmpleado =serviceEmpleado;
    }
    @GetMapping
    public List<Empleado> getEmpleados(){

        return this.serviceEmpleado.getEmpleados();
    }

    @PostMapping
    public ResponseEntity <Object>registrarEmpleado(@RequestBody Empleado empleados){
        return this.serviceEmpleado.newEmpleado(empleados);

    }
    @PutMapping
    public ResponseEntity <Object>actualizarEmpleado(@RequestBody Empleado empleados){
        return this.serviceEmpleado.newEmpleado(empleados);

    }
    @DeleteMapping(path = "{empleadosId}")
    public ResponseEntity<Object> eliminarEmpleado(@PathVariable("empleadosId") Long id) {
        return this.serviceEmpleado.deleteEmpleado(id);
    }


}
