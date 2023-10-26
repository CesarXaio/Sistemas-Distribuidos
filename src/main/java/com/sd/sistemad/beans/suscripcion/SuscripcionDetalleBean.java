package com.sd.sistemad.beans.suscripcion;


import com.sd.sistemad.beans.base.AbstractBean;
import com.sd.sistemad.beans.deporte.DeporteBean;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "suscripcionDetalle")
@Data
public class SuscripcionDetalleBean extends AbstractBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suscripcionDetalleID;

    // Relación con Suscripciones como Cabecera-Detalle
    @ManyToOne
    @JoinColumn(name = "suscripcionid")
    private SuscripcionBean suscripcion;

    // Relación con Deporte o Disciplina
    @ManyToOne
    @JoinColumn(name = "deporteid")
    private DeporteBean deporte;
}
