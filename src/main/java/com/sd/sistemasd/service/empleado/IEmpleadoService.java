package com.sd.sistemasd.service.empleado;

import com.sd.sistemasd.dto.empleado.EmpleadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmpleadoService {
    EmpleadoDTO createEmpleado(EmpleadoDTO empleadoDTO);

    EmpleadoDTO getEmpleadoById(Long id);
    Page<EmpleadoDTO> getAllEmpleados(Pageable pageable);
    EmpleadoDTO updateEmpleado(Long id, EmpleadoDTO empleadoDTO);
    void deleteEmpleado(Long id);
}
