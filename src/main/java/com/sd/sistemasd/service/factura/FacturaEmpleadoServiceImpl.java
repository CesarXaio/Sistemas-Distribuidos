package com.sd.sistemasd.service.factura;

import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import com.sd.sistemasd.beans.facturacion.FacturaEmpleadoBean;
import com.sd.sistemasd.beans.facturacion.FacturaEmpleadoDetalleBean;
import com.sd.sistemasd.dao.empleado.EmpleadoDAO;
import com.sd.sistemasd.dao.factura.FacturaEmpleadoDetalleDAO;
import org.modelmapper.ModelMapper;
import com.sd.sistemasd.dao.factura.FacturaEmpleadoDAO;
import com.sd.sistemasd.dto.factura.FacturaEmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaEmpleadoServiceImpl implements IFacturaEmpleadoService{

    @Autowired
    FacturaEmpleadoDAO facturaDAO;

    @Autowired
    EmpleadoDAO empleadoDAO;

    @Autowired
    FacturaEmpleadoDetalleDAO detallesDAO;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public FacturaEmpleadoDTO create(FacturaEmpleadoDTO factura) {
        FacturaEmpleadoBean facturaBean = toBean(factura);
        FacturaEmpleadoBean facturaSave = facturaDAO.save(facturaBean);
        List<FacturaEmpleadoDetalleBean> detalles = facturaSave.getDetalles();
        if(detalles != null){
            for(FacturaEmpleadoDetalleBean detalle : detalles){
                detalle.setFacturaEmpleado(facturaSave);
            }
        }
        detallesDAO.saveAll(detalles);
        return toDTO(facturaSave);
    }

    private FacturaEmpleadoBean toBean(FacturaEmpleadoDTO dto){
        FacturaEmpleadoBean bean = modelMapper.map(dto, FacturaEmpleadoBean.class);
        EmpleadoBean empleado = empleadoDAO.findById(dto.getEmpleadoid()).orElse(null);
        if(empleado != null){
            bean.setEmpleado(empleado);
        }
        return bean;
    }

    private FacturaEmpleadoDTO toDTO(FacturaEmpleadoBean bean){
        FacturaEmpleadoDTO dto = modelMapper.map(bean, FacturaEmpleadoDTO.class);
        if(bean.getEmpleado() != null){
            dto.setEmpleadoid(bean.getEmpleado().getEmpleadoID());
        }
        return dto;
    }
}
