package com.sd.sistemasd.dto.factura;

import com.sd.sistemasd.dto.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FacturaEmpleadoDTO extends BaseDTO {
    private Long facturaid;
    private Date fecha;
    private String nombreEmpleado;
    private String rucEmpleado;
    private double total;
    private Long empleadoid;
    private List<FactEmpleadoDetalleDTO> detalles;

}
