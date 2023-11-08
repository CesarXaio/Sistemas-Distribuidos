package com.sd.sistemasd.beans.facturacion;


import com.sd.sistemasd.beans.base.AbstractBean;
import com.sd.sistemasd.beans.cliente.ClienteBean;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "facturacionCliente")
@Data
public class FacturaClienteBean extends AbstractBean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facturaClienteid", nullable = false, unique = true)
    private Long facturaClienteID;

    @Column(name = "fechaFacturacion", nullable = true)
    private Date fechaFacturacion;

    @Column(name = "montoTotal", nullable = true)
    private double montoTotal;

    @Column(name = "estadoPago", nullable = true)
    private boolean estadoPago;

    @Column(name = "fechaPago", nullable = true)
    private Date fechaPago;

    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "clienteid")
    private ClienteBean cliente;
}
