package com.sd.sistemasd.service.empleado;

import com.sd.sistemasd.beans.deporte.DeporteBean;
import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import com.sd.sistemasd.dao.empleado.EmpleadoDAO;
import com.sd.sistemasd.dto.empleado.EmpleadoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sd.sistemasd.dao.deporte.DeporteDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
//Comento el cache porque me genera errores ahora
@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private DeporteDAO deporteDAO;


    private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public EmpleadoDTO createEmpleado(EmpleadoDTO empleadoDTO) {


        try {
            logger.debug("Iniciando transacción para crear empleado...");
            System.out.println("Creando empleado");
            EmpleadoBean empleado = new EmpleadoBean();
            empleado.setNombreEmpleado(empleadoDTO.getNombreEmpleado());
            empleado.setApellido(empleadoDTO.getApellido());
            empleado.setCorreoElectronico(empleadoDTO.getCorreoElectronico());
            empleado.setTelefono(empleadoDTO.getTelefono());
            empleado.setHoraInicio(empleadoDTO.getHoraInicio());
            empleado.setHoraFin(empleadoDTO.getHoraFin());
            empleado.setTipoEmpleado(empleadoDTO.getTipoEmpleado());

            // Guardar el empleado en la base de datos
            empleado = empleadoDAO.save(empleado);
            logger.debug("Transacción para crear empleado completada con éxito.");
            // Verificar si el deporteID es válido
            if (empleadoDTO.getDeporteID() != null) {
                Optional<DeporteBean> deporteOptional = deporteDAO.findById(empleadoDTO.getDeporteID());
                if (deporteOptional.isPresent()) {
                    // Asignar el deporte al empleado
                    empleado.setDeporte(deporteOptional.get());
                } else {
                    logger.error("No se crea el empleado: Deporte no válido");
                    // No lanzar excepción, dejar que la excepción de transacción se propague
                }
            }

            // Guardar el empleado nuevamente después de asignar el deporte
            empleado = empleadoDAO.save(empleado);

            return convertToDTO(empleado);
        } catch (Exception e) {
            // El rollback ocurrirá automáticamente debido a la excepción en la transacción
            logger.error("Error durante la creación de empleado: " + e.getMessage());
            throw e; // Permite que la excepción se propague
        }
    }


    //Este metodo es de solo lectura
    @Transactional(readOnly = true)
    @Override
    //@Cacheable(cacheNames = "empleadoCache", key = "#id")
    public EmpleadoDTO getEmpleadoById(Long id) {
        EmpleadoBean empleado = empleadoDAO.findById(id).orElse(null);
        if (empleado != null) {
            return convertToDTO(empleado);
        }
        else {
            return null;
        }
    }

    //Este metodo es de solo lectura
    @Transactional(readOnly = true)
    @Override
    //@Cacheable(cacheNames = "empleadoCache")
    public Page<EmpleadoDTO> getAllEmpleados(Pageable pageable) {
        Page<EmpleadoBean> empleadoPage = empleadoDAO.findAll(pageable);
        return empleadoPage.map(this::convertToDTO);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
   // @CachePut(cacheNames = "empleadoCache", key = "#id")
    public EmpleadoDTO updateEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        EmpleadoBean empleado = empleadoDAO.findById(id).orElse(null);
        if (empleado == null) {
            // No se encontró al empleado, lanzar una excepción EntityNotFoundException
            throw new EntityNotFoundException("Empleado no encontrado con ID: " + id);
        }

        try {
            empleado.setNombreEmpleado(empleadoDTO.getNombreEmpleado());
            empleado.setApellido(empleadoDTO.getApellido());
            empleado.setTelefono(empleadoDTO.getTelefono());
            empleado.setHoraInicio(empleadoDTO.getHoraInicio());
            empleado.setHoraFin(empleadoDTO.getHoraFin());
            empleado.setTipoEmpleado(empleadoDTO.getTipoEmpleado());
            return convertToDTO(empleado);
        } catch (Exception e) {
            logger.error("Error durante la actualización del empleado: " + e.getMessage());
            throw e; // Permite que la excepción se propague
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(cacheNames = "empleadoCache", key = "#id" )
    public void deleteEmpleado(Long id) {
        empleadoDAO.deleteById(id);
        logger.info("Empleado eliminado");  //Avisamos que se elimino el empleado
    }

    //convertimos en DTO
    private EmpleadoDTO convertToDTO(EmpleadoBean empleado){
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setEmpleadoID(empleado.getEmpleadoID());
        dto.setNombreEmpleado(empleado.getNombreEmpleado());
        dto.setApellido(empleado.getApellido());
        dto.setCorreoElectronico(empleado.getCorreoElectronico());
        dto.setTelefono(empleado.getTelefono());
        dto.setHoraInicio(empleado.getHoraInicio());
        dto.setHoraFin(empleado.getHoraFin());
        dto.setTipoEmpleado(empleado.getTipoEmpleado());
        return dto;
    }
}
