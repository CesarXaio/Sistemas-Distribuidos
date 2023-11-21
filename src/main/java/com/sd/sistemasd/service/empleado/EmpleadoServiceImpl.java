package com.sd.sistemasd.service.empleado;

import com.sd.sistemasd.beans.deporte.DeporteBean;
import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import com.sd.sistemasd.beans.role.RoleBean;
import com.sd.sistemasd.dao.empleado.EmpleadoDAO;
import com.sd.sistemasd.dao.role.RoleDao;
import com.sd.sistemasd.dto.empleado.EmpleadoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sd.sistemasd.dao.deporte.DeporteDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//Comento el cache porque me genera errores ahora
@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public EmpleadoDTO createEmpleado(EmpleadoDTO empleadoDTO) {
        if(empleadoDAO.findByEmail(empleadoDTO.getEmail()).isPresent()){
            return null;
        }
        EmpleadoBean empleadoBean = new EmpleadoBean();
        empleadoBean.setNombre(empleadoDTO.getNombre());
        empleadoBean.setCedula(empleadoDTO.getCedula());
        empleadoBean.setEmail(empleadoDTO.getEmail());
        empleadoBean.setPassword(passwordEncoder.encode(empleadoDTO.getPassword()));
        empleadoBean.setTelefono(empleadoDTO.getTelefono());


        Set<RoleBean> roles = new HashSet<>();

        String roleName = empleadoDTO.getRole();
        if(roleName != null){
            RoleBean role = roleDao.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(role);
        } else{
            roles.add(roleDao.findByName("ROLE_EMPLEADO").orElseThrow(() -> new RuntimeException("Role not found")));
        }
        empleadoBean.setRoles(roles);

        empleadoBean = empleadoDAO.save(empleadoBean);
        return convertToDTO(empleadoBean);

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
        EmpleadoBean empleadoBean = empleadoDAO.findById(id).orElse(null);
        if (empleadoBean == null) {
            // No se encontró al empleado, lanzar una excepción EntityNotFoundException
            throw new EntityNotFoundException("Empleado no encontrado con ID: " + id);
        }

        try {
            empleadoBean.setNombre(empleadoDTO.getNombre());
            empleadoBean.setCedula(empleadoDTO.getCedula());
            empleadoBean.setEmail(empleadoBean.getEmail());
            empleadoBean.setPassword(passwordEncoder.encode(empleadoDTO.getPassword()));
            empleadoBean.setTelefono(empleadoBean.getTelefono());
            return convertToDTO(empleadoBean);
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
        dto.setNombre(empleado.getNombre());
        dto.setEmail(empleado.getEmail());
        dto.setTelefono(empleado.getTelefono());
        dto.setPassword(empleado.getPassword());
        dto.setCedula(empleado.getCedula());
        dto.setRole(empleado.getRoles().toString());
        return dto;
    }
}
