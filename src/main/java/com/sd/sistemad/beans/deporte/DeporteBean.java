package com.sd.sistemad.beans.deporte;


import com.sd.sistemad.beans.base.AbstractBean;
import com.sd.sistemad.beans.suscripcion.SuscripcionDetalleBean;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "deporte")
@Data
public class DeporteBean extends AbstractBean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deporteid", nullable = false, unique = true)
    private Long deporteID;

    @Column(name = "nombre", nullable = true)
    private String nombre;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    // Relación con Asignación de Entrenadores por Disciplina
    //@OneToMany(mappedBy = "deporte")
    //private List<AsignacionEntrenadorBean> asignacionesEntrenador;

    // Relación con Detalle Suscripción
    @OneToMany(mappedBy = "deporte")
    private List<SuscripcionDetalleBean> suscripcionDetalles;

}
