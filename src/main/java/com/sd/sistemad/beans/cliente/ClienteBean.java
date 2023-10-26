package com.sd.sistemad.beans.cliente;


import com.sd.sistemad.beans.base.AbstractBean;
import com.sd.sistemad.beans.facturacion.FacturaClienteBean;
import com.sd.sistemad.beans.suscripcion.SuscripcionBean;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;



@Entity
@Table (name = "cliente")
@Data
public class ClienteBean extends AbstractBean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clieteid", nullable = false, unique = true)
    private Long clienteID;

    @Column(name = "nombre", nullable = true)
    private String nombre;

    @Column(name = "apellido", nullable = true)
    private String apellido;

    @Column(name = "correo", nullable = true)
    private String correoElectronico;

    @Column(name = "telefono", nullable = true)
    private String telefono;


    // Relación con Facturación-Cliente
    @OneToMany(mappedBy = "cliente")
    private List<FacturaClienteBean> facturasCliente;

    // Relación con Suscripciones
    @OneToMany(mappedBy = "cliente")
    private List<SuscripcionBean> suscripciones;

}
