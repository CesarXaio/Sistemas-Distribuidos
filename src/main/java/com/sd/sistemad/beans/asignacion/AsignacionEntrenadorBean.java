package com.sd.sistemad.beans.asignacion;


import com.sd.sistemad.beans.base.AbstractBean;
import com.sd.sistemad.beans.deporte.DeporteBean;
import com.sd.sistemad.beans.empleado.EmpleadoBean;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "asignacionEntrenador")
@Data
public class AsignacionEntrenadorBean extends AbstractBean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asignacionid", nullable = false, unique = true)
    private Long asignacionID;

    // Relación con Deporte
    @ManyToOne
    @JoinColumn(name = "deporteid")
    private DeporteBean deporte;

    public Long getAsignacionID() {
        return asignacionID;
    }

    public void setAsignacionID(Long asignacionID) {
        this.asignacionID = asignacionID;
    }

    public DeporteBean getDeporte() {
        return deporte;
    }

    public void setDeporte(DeporteBean deporte) {
        this.deporte = deporte;
    }
/*
    // Corrige la relación con Empleado (Entrenador) para apuntar a EmpleadoBean
    @ManyToOne
    @JoinColumn(name = "empleadoid")
    private EmpleadoBean empleado;
*/
    // Otros atributos y métodos
}
