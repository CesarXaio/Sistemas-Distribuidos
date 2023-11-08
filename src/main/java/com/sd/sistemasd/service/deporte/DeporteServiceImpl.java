package com.sd.sistemasd.service.deporte;


import com.sd.sistemasd.beans.deporte.DeporteBean;
import com.sd.sistemasd.dao.deporte.DeporteDAO;
import com.sd.sistemasd.dto.deporte.DeporteDTO;
import com.sd.sistemasd.service.empleado.EmpleadoServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DeporteServiceImpl implements IDeporteService {

    @Autowired
    DeporteDAO deporteDAO;

    private static final Logger logger = LoggerFactory.getLogger(DeporteServiceImpl.class);
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public DeporteDTO createDeporte(DeporteDTO deporteDTO) {
        try {
            DeporteBean deporte = new DeporteBean();
            deporte.setNombreDeporte(deporteDTO.getNombre());
            deporte.setDescripcion(deporteDTO.getDescripcion());
            deporte = deporteDAO.save(deporte);
            return convertToDTO(deporte);
        }catch (Exception e){
            logger.error("Error durante la creacion  del deporte: " + e.getMessage());
            throw e; // Permite que la excepción se propague
        }
    }

    //Este metodo es de solo lectura
    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "deportesCache", key = "#id")
    public DeporteDTO getDeporteById(Long id) {
        DeporteBean deporte = deporteDAO.findById(id).orElse(null);
        if(deporte != null){
            return convertToDTO(deporte);
        }
        return null;
    }

    //Este metodo es de solo lectura
    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "deportesCache")
    public Page<DeporteDTO> getAllDeportes(Pageable pageable) {
        Page<DeporteBean> deportesPage = deporteDAO.findAll(pageable);
        return deportesPage.map(this::convertToDTO);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    @CachePut(value = "deportesCache", key = "#id")
    public DeporteDTO updateDeporte(Long id, DeporteDTO deporteDTO) {
        DeporteBean existingDeporte = deporteDAO.findById(id).orElse(null);
        if (existingDeporte == null) {
            throw new EntityNotFoundException("Deporte no encontrado con ID: " + id);
        }

        existingDeporte.setNombreDeporte(deporteDTO.getNombre());
        existingDeporte.setDescripcion(deporteDTO.getDescripcion());

        try {
            existingDeporte = deporteDAO.save(existingDeporte);
            return convertToDTO(existingDeporte);
        } catch (Exception e) {
            logger.error("Error durante la actualización del deporte: " + e.getMessage());
            throw e; // Permite que la excepción se propague
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(value = "deportesCache", key = "#id")
    public void deleteDeporte(Long id) {
        deporteDAO.deleteById(id);
        logger.info("Deporte eliminado");
    }

    private DeporteDTO convertToDTO(DeporteBean deporte) {
        DeporteDTO dto = new DeporteDTO();
        dto.setDeporteID(deporte.getDeporteID());
        dto.setNombre(deporte.getNombreDeporte());
        dto.setDescripcion(deporte.getDescripcion());
        return dto;
    }
}
