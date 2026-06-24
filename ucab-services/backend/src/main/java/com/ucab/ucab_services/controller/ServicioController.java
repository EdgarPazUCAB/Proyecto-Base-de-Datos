package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Servicio;
import com.ucab.ucab_services.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable String id) {
        Servicio servicio = servicioService.findById(id);
        return servicio != null ? ResponseEntity.ok(servicio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Servicio createServicio(@RequestBody Servicio servicio) {
        return servicioService.save(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable String id, @RequestBody Servicio servicioDetails) {
        Servicio servicio = servicioService.findById(id);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.setCodigoServicio(servicioDetails.getCodigoServicio());
        servicio.setDescripcionDetallada(servicioDetails.getDescripcionDetallada());
        servicio.setRequisitos(servicioDetails.getRequisitos());
        servicio.setEstadoServicio(servicioDetails.getEstadoServicio());
        servicio.setPerfilSolicitante(servicioDetails.getPerfilSolicitante());
        // Note: entidadPrestadora is a relationship, we might need to set it by id, but for simplicity we leave it as is.
        Servicio updatedServicio = servicioService.save(servicio);
        return ResponseEntity.ok(updatedServicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable String id) {
        servicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}