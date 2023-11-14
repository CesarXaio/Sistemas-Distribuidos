package com.sd.sistemasd.controllers.empleado;

import com.sd.sistemasd.dto.empleado.EmpleadoDTO;
import com.sd.sistemasd.service.empleado.IEmpleadoService;
import com.sd.sistemasd.utils.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/empleados")
@CrossOrigin
public class EmpleadoController {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
    @Autowired
    private IEmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<?> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        try {
            EmpleadoDTO createEmpleado = empleadoService.createEmpleado(empleadoDTO);
            return new ResponseEntity<>(createEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error al crear el empleado", e);
            return new ResponseEntity<>( "Error al crear el Empleado",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public EmpleadoDTO getEmpleadoById(@PathVariable Long id){
        return empleadoService.getEmpleadoById(id);
    }



    @GetMapping("/page/{page}")
    public Page<EmpleadoDTO> getAllEmpleados(@PathVariable(name = "page") int page) {
        int size = Setting.PAGE_SIZE;
        Pageable pageable = PageRequest.of(page, size);
        return empleadoService.getAllEmpleados(pageable);
        //En caso de que haya problemas futuros, agregar getContent();q
    }

    @PutMapping("/{id}")
    public EmpleadoDTO updateEmplado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
        return empleadoService.updateEmpleado(id, empleadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable Long id) {

        empleadoService.deleteEmpleado(id);
        return ResponseEntity.ok("Empleado con id " + id + " eliminado");
    }
}
