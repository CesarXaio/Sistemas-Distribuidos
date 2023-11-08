package com.sd.sistemasd.controllers.entrenador;

import com.sd.sistemasd.controllers.empleado.EmpleadoController;
import com.sd.sistemasd.dto.entrenador.EntrenadorDTO;
import com.sd.sistemasd.service.entrenador.EntrenadorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/asignaciones")
public class EntrenadorController {
    //private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private EntrenadorServiceImpl entrenadorService;

    @GetMapping("/{id}")
    public EntrenadorDTO getAsignacionEntrenadorById(@PathVariable Long id) {

        return entrenadorService.getAsignacionEntrenadorById(id);
    }

}
