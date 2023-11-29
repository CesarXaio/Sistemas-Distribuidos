package com.sd.sistemasd.dto.factura;

import lombok.Data;

@Data
public class FactEmpleadoDetalleDTO {
    private Long detalleid;
    private double subtotal;
    private Long facturaid;
}
