package com.sd.sistemasd.dto.entrenador;

import com.sd.sistemasd.dto.BaseDTO;
import com.sd.sistemasd.dto.deporte.DeporteDTO;
import com.sd.sistemasd.dto.empleado.EmpleadoDTO;
import lombok.Data;

@Data
public class EntrenadorDTO extends BaseDTO {
    private Long asignacionID;
    private EmpleadoDTO entrenador;
    private DeporteDTO deporte;
}
