package com.sd.sistemasd.dao.empleado;

import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoDAO extends JpaRepository<EmpleadoBean, Long> {
    Page<EmpleadoBean> findAll(Pageable pageable);

    Optional<EmpleadoBean> findOneByCorreoElectronico(String correoElectronico);

}
