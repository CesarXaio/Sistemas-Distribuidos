package com.sd.sistemad.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Entrenador")
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entrenadorID;

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Long getEntrenadorID() {
        return entrenadorID;
    }

    public void setEntrenadorID(Long entrenadorID) {
        this.entrenadorID = entrenadorID;
    }

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "deporte_id") // El nombre de la columna de relación con el deporte
    private Deporte deporte;

}
