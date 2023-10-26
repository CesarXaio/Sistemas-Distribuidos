package com.sd.sistemad.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.sistemad.beans.asignacion.AsignacionEntrenadorBean;
import com.sd.sistemad.beans.facturacion.FacturaEmpleadoBean;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empleadoID;
    private String nombre;
    private String apellido;

    private String telefono;
    private String horaInicio;
    private String horaFin;
    private String tipoEmpleado;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "asignacionid")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AsignacionEntrenadorBean> detalles = new ArrayList<>();
    //private List<FacturaEmpleadoBean> facturasEntrenador;
    //private List<AsignacionEntrenadorBean> asignacionesEntrenador;

    // Constructor, getters y setters aquí

    public Long getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(Long empleadoID) {
        this.empleadoID = empleadoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
/*
    public List<FacturaEmpleadoBean> getFacturasEntrenador() {
        return facturasEntrenador;
    }

    public void setFacturasEntrenador(List<FacturaEmpleadoBean> facturasEntrenador) {
        this.facturasEntrenador = facturasEntrenador;
    }

    public List<AsignacionEntrenadorBean> getAsignacionesEntrenador() {
        return asignacionesEntrenador;
    }

    public void setAsignacionesEntrenador(List<AsignacionEntrenadorBean> asignacionesEntrenador) {
        this.asignacionesEntrenador = asignacionesEntrenador;
    }
    */

    public List<AsignacionEntrenadorBean> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<AsignacionEntrenadorBean> detalles) {
        this.detalles = detalles;
    }

    public Empleado() {

    }
// Asegúrate de tener un constructor por defecto, getters y setters para todas las propiedades.
}
