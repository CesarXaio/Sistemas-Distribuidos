package com.sd.sistemasd.dao.entrenador;

import com.sd.sistemasd.beans.asignacion.AsignacionEntrenadorBean;
import com.sd.sistemasd.beans.suscripcion.SuscripcionBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorDAO extends JpaRepository<AsignacionEntrenadorBean,Long> {
    Page<AsignacionEntrenadorBean> findAll(Pageable pageable);
}
