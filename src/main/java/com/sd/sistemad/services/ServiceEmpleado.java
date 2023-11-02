package com.sd.sistemad.services;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.repositories.IEmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;


import org.springframework.stereotype.Service;



import java.util.ArrayList;

import java.util.Optional;


@Service
public class ServiceEmpleado {

    @Autowired
    IEmpleadoRepository empleadoRepository;


   @Cacheable(value = "getEmpleados")
    public ArrayList<Empleado> getEmpleados(){

       return (ArrayList<Empleado>) empleadoRepository.findAll();
    }


    public Empleado guardarEmpleado (Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    @Cacheable(value = "getById", key = "#id")
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



}