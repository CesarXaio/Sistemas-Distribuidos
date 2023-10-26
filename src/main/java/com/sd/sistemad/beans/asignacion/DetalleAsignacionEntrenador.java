
package com.sd.sistemad.beans.asignacion;

import com.sd.sistemad.beans.base.AbstractBean;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "detalleAsignacionEntrenador")
@Data
public class DetalleAsignacionEntrenador extends AbstractBean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalleid", nullable = false, unique = true)
    private Long detalleID;

    // Puedes agregar otros atributos específicos de los detalles aquí

    // Relación con la AsignacionEntrenadorBean (cabecera)
    @ManyToOne
    @JoinColumn(name = "asignacionid")
    private AsignacionEntrenadorBean asignacionEntrenador;
}
