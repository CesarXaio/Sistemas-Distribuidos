package com.sd.sistemad.services;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.repositories.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class ServiceEmpleado {
    HashMap<String, Object> datos;
    private final IEmpleadoRepository empleadoRepository;

    @Autowired
    public ServiceEmpleado(IEmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;

    }

    public List<Empleado> getEmpleados() {
        return this.empleadoRepository.findAll(); // Utiliza el método findAll proporcionado por JpaRepository
    }

    public ResponseEntity<Object> newEmpleado(Empleado empleado) {
        Optional<Empleado> respuesta = empleadoRepository.findById(empleado.getEmpleadoID());
        datos = new HashMap<>();

        if (respuesta.isPresent()) {
            datos.put("error", true);
            datos.put("message", "Ya existe un empleado con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        datos.put("message", "Se guardó con éxito");
        if (empleado.getEmpleadoID() != null) {
            datos.put("message", "Se actualizó con éxito");
        }

        // Debes guardar la instancia de 'empleados' en lugar de 'respuesta'
        empleadoRepository.save(empleado);
        datos.put("data", empleado);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }


    public ResponseEntity<Object> deleteEmpleado(Long id) {
        datos = new HashMap<>();
        boolean existe = this.empleadoRepository.existsById(id);
        if (!existe) {
            datos.put("error", true);
            datos.put("message", "No existe un empleado con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        empleadoRepository.deleteById(id);
        datos.put("message", "Empleado eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

}
