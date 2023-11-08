package com.sd.sistemasd.controllers.deporte;

import com.sd.sistemasd.dto.deporte.DeporteDTO;
import com.sd.sistemasd.service.deporte.IDeporteService;
import com.sd.sistemasd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deportes")
public class DeporteController {
    @Autowired
    IDeporteService deporteService;

    @PostMapping
    public DeporteDTO createDeporte(@RequestBody DeporteDTO deporteDTO) {
        return deporteService.createDeporte(deporteDTO);
    }

    @GetMapping("/{id}")
    public DeporteDTO getDeporteById(@PathVariable Long id) {
        return deporteService.getDeporteById(id);
    }

    @GetMapping("/page/{page}")
    public List<DeporteDTO> getAllDeportes(@PathVariable(name = "page") int page) {
        int size = Setting.PAGE_SIZE;
        Pageable pageable = PageRequest.of(page, size);
        return deporteService.getAllDeportes(pageable).getContent();
    }

    @PutMapping("/{id}")
    public DeporteDTO updateDeporte(@PathVariable Long id, @RequestBody DeporteDTO deporteDTO) {
        return deporteService.updateDeporte(id, deporteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDeporte(@PathVariable Long id) {
        deporteService.deleteDeporte(id);
    }
}
