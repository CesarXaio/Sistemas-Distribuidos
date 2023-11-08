package com.sd.sistemasd.dto.deporte;

import com.sd.sistemasd.dto.BaseDTO;
import lombok.Data;

@Data
public class DeporteDTO extends BaseDTO {
    private Long deporteID;
    private String nombre;
    private String descripcion;
}
