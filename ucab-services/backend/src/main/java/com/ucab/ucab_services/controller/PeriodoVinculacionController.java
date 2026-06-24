package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import com.ucab.ucab_services.service.PeriodoVinculacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/periodos-vinculacion")
public class PeriodoVinculacionController {
    @Autowired
    private PeriodoVinculacionService periodoVinculacionService;

    @GetMapping
    public List<PeriodoVinculacion> getAllPeriodosVinculacion() {
        return periodoVinculacionService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<PeriodoVinculacion> getPeriodoVinculacionById(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Date fechaInicio) {
        PeriodoVinculacionId id = new PeriodoVinculacionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaInicio(fechaInicio);
        Optional<PeriodoVinculacion> periodoVinculacion = periodoVinculacionService.findById(id);
        return periodoVinculacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PeriodoVinculacion createPeriodoVinculacion(@RequestBody PeriodoVinculacion periodoVinculacion) {
        return periodoVinculacionService.save(periodoVinculacion);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PeriodoVinculacion> updatePeriodoVinculacion(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Date fechaInicio, @RequestBody PeriodoVinculacion periodoVinculacionDetails) {
        PeriodoVinculacionId id = new PeriodoVinculacionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaInicio(fechaInicio);
        Optional<PeriodoVinculacion> periodoVinculacionOptional = periodoVinculacionService.findById(id);
        if (periodoVinculacionOptional.isPresent()) {
            PeriodoVinculacion periodoVinculacion = periodoVinculacionOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(periodoVinculacionService.save(periodoVinculacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deletePeriodoVinculacion(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Date fechaInicio) {
        PeriodoVinculacionId id = new PeriodoVinculacionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaInicio(fechaInicio);
        if (periodoVinculacionService.existsById(id)) {
            periodoVinculacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}