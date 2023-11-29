package com.sd.sistemasd.service.factura;

import com.sd.sistemasd.beans.facturacion.FacturaEmpleadoBean;
import com.sd.sistemasd.beans.facturacion.FacturaEmpleadoDetalleBean;
import com.sd.sistemasd.dao.factura.FacturaEmpleadoDAO;
import com.sd.sistemasd.dto.factura.FactEmpleadoDetalleDTO;
import com.sd.sistemasd.dto.factura.FacturaEmpleadoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactEmpleadoDetalleImpl implements IFactEmpleadoDetalleService{
    @Autowired
    FacturaEmpleadoDAO facturaDAO;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public FactEmpleadoDetalleDTO create(FactEmpleadoDetalleDTO detalle) {
        return null;
    }

    FacturaEmpleadoDetalleBean toBean(FactEmpleadoDetalleDTO dto){
        FacturaEmpleadoDetalleBean bean = modelMapper.map(dto, FacturaEmpleadoDetalleBean.class);
        FacturaEmpleadoBean facturaBean = facturaDAO.findById(dto.getFacturaid()).orElse(null);
        if (facturaBean != null){
            bean.setFacturaEmpleado(facturaBean);
        }
        return bean;
    }

    FactEmpleadoDetalleDTO toDTO(FacturaEmpleadoDetalleBean bean){
        FactEmpleadoDetalleDTO dto = modelMapper.map(bean, FactEmpleadoDetalleDTO.class);
        if(bean.getFacturaEmpleado() != null) {
            dto.setFacturaid(bean.getFacturaEmpleado().getFacturaid());
        }
        return dto;
    }
}
