package com.sd.sistemasd.service.entrenador;

import com.sd.sistemasd.beans.asignacion.AsignacionEntrenadorBean;
import com.sd.sistemasd.beans.deporte.DeporteBean;
import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import com.sd.sistemasd.dao.entrenador.EntrenadorDAO;
import com.sd.sistemasd.dto.deporte.DeporteDTO;
import com.sd.sistemasd.dto.empleado.EmpleadoDTO;
import com.sd.sistemasd.dto.entrenador.EntrenadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntrenadorServiceImpl {
    @Autowired
    private EntrenadorDAO entrenadorDAO;

    @Transactional(readOnly = true)
   // @Cacheable(cacheNames = "EntrenadorCache", key = "#id")
    public EntrenadorDTO getAsignacionEntrenadorById(Long asignacionID) {
        AsignacionEntrenadorBean asignacion = entrenadorDAO.findById(asignacionID).orElse(null);
        if (asignacion != null) {
            EntrenadorDTO asignacionDTO = new EntrenadorDTO();
            asignacionDTO.setAsignacionID(asignacion.getAsignacionID());
            asignacionDTO.setEntrenador(convertToDTO(asignacion.getEntrenador()));
            asignacionDTO.setDeporte(convertToDTO(asignacion.getDeporte()));
            System.out.println("Se devuelve el entrenador");
            return asignacionDTO;
        } else {
            return null;
        }
    }

    private DeporteDTO convertToDTO(DeporteBean deporte) {
        DeporteDTO dto = new DeporteDTO();
        dto.setDeporteID(deporte.getDeporteID());
        dto.setNombre(deporte.getNombreDeporte());
        dto.setDescripcion(deporte.getDescripcion());
        return dto;
    }
    private EmpleadoDTO convertToDTO(EmpleadoBean empleado){
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setEmpleadoID(empleado.getEmpleadoID());
        dto.setApellido(empleado.getApellido());
        dto.setTelefono(empleado.getTelefono());
        dto.setHoraInicio(empleado.getHoraInicio());
        dto.setHoraFin(empleado.getHoraFin());
        dto.setTipoEmpleado(empleado.getTipoEmpleado());
        return dto;
    }


}
