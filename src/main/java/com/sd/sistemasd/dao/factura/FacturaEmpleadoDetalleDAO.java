package com.sd.sistemasd.dao.factura;

import com.sd.sistemasd.beans.facturacion.FacturaEmpleadoDetalleBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaEmpleadoDetalleDAO extends JpaRepository<FacturaEmpleadoDetalleBean, Long> {
}
