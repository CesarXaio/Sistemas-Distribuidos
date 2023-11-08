package com.sd.sistemasd.service.entrenador;

import com.sd.sistemasd.dto.empleado.EmpleadoDTO;
import com.sd.sistemasd.dto.entrenador.EntrenadorDTO;

public interface IEntrenadorService {
    EntrenadorDTO getEmpleadoById(Long id);
}
