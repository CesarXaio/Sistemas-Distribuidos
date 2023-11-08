package com.sd.sistemasd.dao.empleado;

import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDAO extends JpaRepository<EmpleadoBean, Long> {
    Page<EmpleadoBean> findAll(Pageable pageable);
}
