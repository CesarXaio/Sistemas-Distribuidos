package com.sd.sistemasd.beans.empleado;


import com.sd.sistemasd.beans.asignacion.AsignacionEntrenadorBean;
import com.sd.sistemasd.beans.base.AbstractBean;
import com.sd.sistemasd.beans.deporte.DeporteBean;
import com.sd.sistemasd.beans.facturacion.FacturaEmpleadoBean;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table (name = "empleado")
@Data
public class EmpleadoBean extends AbstractBean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleadoid", nullable = false, unique = true)
    private Long empleadoID;

    @Column(name = "nombre")
    private String nombreEmpleado;

//    @Column(name = "password")
//    private String password;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correoElectronico;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "horaInicio")
    private String horaInicio;

    @Column(name = "horaFin")
    private String horaFin;

    @Column(name = "tipoEmpleado")
    private String tipoEmpleado;


    @ManyToOne
    @JoinColumn(name = "deporte_id") // Nombre de la columna que almacena el ID del deporte en la tabla de Empleados
    private DeporteBean deporte;

    // Relación con Facturación-Entrenador
    @OneToMany(mappedBy = "entrenador")
    private List<FacturaEmpleadoBean> facturasEntrenador;

    // Relación con Asignación de Entrenadores por Disciplina
    @OneToMany(mappedBy = "entrenador")
    private List<AsignacionEntrenadorBean> asignacionesEntrenador;
}
