package com.sd.sistemasd.dto.empleado;

import com.sd.sistemasd.dto.BaseDTO;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmpleadoDTO extends BaseDTO {
    private Long empleadoID;
    private String nombre;
    private String telefono;
    private String cedula;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String role;
    public EmpleadoDTO() {
    }


}
