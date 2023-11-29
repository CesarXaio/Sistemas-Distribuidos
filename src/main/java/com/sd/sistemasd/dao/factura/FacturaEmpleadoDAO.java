package com.sd.sistemasd.dao.factura;

import com.sd.sistemasd.beans.facturacion.FacturaEmpleadoBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface FacturaEmpleadoDAO extends JpaRepository<FacturaEmpleadoBean, Long> {
    Page<FacturaEmpleadoBean> findAll(Pageable pageable);
}
