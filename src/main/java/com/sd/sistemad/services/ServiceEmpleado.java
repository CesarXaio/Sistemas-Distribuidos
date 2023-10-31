package com.sd.sistemad.services;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.repositories.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class ServiceEmpleado {

    @Autowired
    IEmpleadoRepository empleadoRepository;

    public ArrayList<Empleado> getEmpleados(){
        return (ArrayList<Empleado>) empleadoRepository.findAll();
    }

    public Empleado guardarEmpleado (Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public  Optional<Empleado> getById(Long id){
        return empleadoRepository.findById(id);
    }

    public Empleado actualizarById(Empleado request, Long id){
        Empleado empleado = empleadoRepository.findById(id).get();
        empleado.setNombre(request.getNombre());
        empleado.setApellido(request.getApellido());
        empleado.setTelefono(request.getTelefono());
        empleado.setHoraInicio(request.getHoraInicio());
        empleado.setHoraFin(request.getHoraFin());
        empleado.setTipoEmpleado(request.getTipoEmpleado());
        return empleado;
    }

    public Boolean eliminarEmpleado(Long id){
        try {
            empleadoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    /*
    HashMap<String, Object> datos;
    private final IEmpleadoRepository empleadoRepository;
    //Usamos paginacion
    public Page<Empleado> getEmpleados(Pageable pageable) {
        return this.empleadoRepository.findAll(pageable);
    }
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
*/
}