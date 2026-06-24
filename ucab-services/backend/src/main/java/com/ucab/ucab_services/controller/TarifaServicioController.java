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

    @GetMapping("/{id}")
    public ResponseEntity<TarifaServicio> getTarifaServicioById(@PathVariable TarifaServicioId id) {
        Optional<TarifaServicio> tarifaServicio = tarifaServicioService.findById(id);
        return tarifaServicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TarifaServicio createTarifaServicio(@RequestBody TarifaServicio tarifaServicio) {
        return tarifaServicioService.save(tarifaServicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaServicio> updateTarifaServicio(@PathVariable TarifaServicioId id, @RequestBody TarifaServicio tarifaServicioDetails) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifaServicio(@PathVariable TarifaServicioId id) {
        if (tarifaServicioService.existsById(id)) {
            tarifaServicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}