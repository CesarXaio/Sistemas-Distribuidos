package com.sd.sistemasd.dto.empleado;

import com.sd.sistemasd.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmpleadoDTO extends BaseDTO {
    private Long empleadoID;
    private String nombreEmpleado;
    private String apellido;
    private String correoElectronico;
    private String telefono;
    private String horaInicio;
    private String horaFin;
    private String tipoEmpleado;
    private Long deporteID;
    //private List<String> entrenador;

    public EmpleadoDTO() {
    }


}
