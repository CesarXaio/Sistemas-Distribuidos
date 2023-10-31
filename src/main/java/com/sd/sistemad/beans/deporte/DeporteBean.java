package com.sd.sistemad.beans.deporte;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.beans.base.AbstractBean;
import com.sd.sistemad.beans.suscripcion.SuscripcionDetalleBean;
import com.sd.sistemad.models.Empleado;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Deporte")

public class DeporteBean extends AbstractBean {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deporteID;
    private String nombre;

    @ManyToOne
    private Empleado empleado;

    public DeporteBean() {
    }

/*    public Long getDeporteID() {
        return deporteID;
    }

    public void setDeporteID(Long deporteID) {
        this.deporteID = deporteID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public List<SuscripcionDetalleBean> getSuscripcionDetalles() {
        return suscripcionDetalles;
    }

    public void setSuscripcionDetalles(List<SuscripcionDetalleBean> suscripcionDetalles) {
        this.suscripcionDetalles = suscripcionDetalles;
    }



    // Relación con Asignación de Entrenadores por Disciplina
    //@OneToMany(mappedBy = "deporte")
    //private List<AsignacionEntrenadorBean> asignacionesEntrenador;

    // Relación con Detalle Suscripción
    @OneToMany(mappedBy = "deporte")
    private List<SuscripcionDetalleBean> suscripcionDetalles;
*/
}
