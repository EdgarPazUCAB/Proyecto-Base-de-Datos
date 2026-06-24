package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import com.ucab.ucab_services.service.TarifaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarifas-servicio")
public class TarifaServicioController {
    @Autowired
    private TarifaServicioService tarifaServicioService;

    @GetMapping
    public List<TarifaServicio> getAllTarifaServicios() {
        return tarifaServicioService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<TarifaServicio> getTarifaServicioById(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio) {
        TarifaServicioId id = new TarifaServicioId();

        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        Optional<TarifaServicio> tarifaServicio = tarifaServicioService.findById(id);
        return tarifaServicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TarifaServicio createTarifaServicio(@RequestBody TarifaServicio tarifaServicio) {
        return tarifaServicioService.save(tarifaServicio);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TarifaServicio> updateTarifaServicio(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestBody TarifaServicio tarifaServicioDetails) {
        TarifaServicioId id = new TarifaServicioId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        Optional<TarifaServicio> tarifaServicioOptional = tarifaServicioService.findById(id);
        if (tarifaServicioOptional.isPresent()) {
            TarifaServicio tarifaServicio = tarifaServicioOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(tarifaServicioService.save(tarifaServicio));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteTarifaServicio(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio) {
        TarifaServicioId id = new TarifaServicioId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        if (tarifaServicioService.existsById(id)) {
            tarifaServicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}