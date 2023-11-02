package com.sd.sistemad.beans.deporte;

import com.sd.sistemad.models.Empleado;
import com.sd.sistemad.beans.base.AbstractBean;
import jakarta.persistence.*;
import com.sd.sistemad.beans.suscripcion.SuscripcionDetalleBean;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Deporte")
@Data
public class DeporteBean extends AbstractBean {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deporteID;
    private String nombre;

    @ManyToOne
    private Empleado empleado;

    // Relación con Detalle Suscripción
    @OneToMany(mappedBy = "deporte")
    private List<SuscripcionDetalleBean> suscripcionDetalles;

}
