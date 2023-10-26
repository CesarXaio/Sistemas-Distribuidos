package com.sd.sistemad.beans.suscripcion;


import com.sd.sistemad.beans.base.AbstractBean;
import com.sd.sistemad.beans.cliente.ClienteBean;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "suscripcion")
@Data
public class SuscripcionBean extends AbstractBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suscripcionid", nullable = false, unique = true)
    private Long suscripcionID;

    @Column(name = "planPago", nullable = true)
    private String planPago;

    @Column(name = "montoTotal", nullable = true)
    private double montoTotal;

    @Column(name = "estado", nullable = true)
    private boolean estado;

    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "clienteid")
    private ClienteBean cliente;

    // Relación con Detalle Suscripción
    @OneToMany(mappedBy = "suscripcion")
    private List<SuscripcionDetalleBean> suscripcionDetalles;
}
