package com.sd.sistemad.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Deporte")
public class Deporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deporteID;
    private String nombre;

    public Long getDeporteID() {
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

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @ManyToOne
    @JoinColumn(name = "entrenador")
    private Entrenador entrenador ;
}
