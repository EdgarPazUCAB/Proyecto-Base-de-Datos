package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import com.ucab.ucab_services.service.EspacioFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/espacios-fisicos")
public class EspacioFisicoController {

    @Autowired
    private EspacioFisicoService espacioFisicoService;

    @GetMapping
    public List<EspacioFisico> getAllEspaciosFisicos() {
        return espacioFisicoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacioFisico> getEspacioFisicoById(@PathVariable EspacioFisicoId id) {
        Optional<EspacioFisico> espacioFisico = espacioFisicoService.findById(id);
        return espacioFisico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EspacioFisico createEspacioFisico(@RequestBody EspacioFisico espacioFisico) {
        return espacioFisicoService.save(espacioFisico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspacioFisico> updateEspacioFisico(@PathVariable EspacioFisicoId id, @RequestBody EspacioFisico espacioFisicoDetails) {
        Optional<EspacioFisico> espacioFisicoOptional = espacioFisicoService.findById(id);
        if (espacioFisicoOptional.isPresent()) {
            EspacioFisico espacioFisico = espacioFisicoOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(espacioFisicoService.save(espacioFisico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspacioFisico(@PathVariable EspacioFisicoId id) {
        if (espacioFisicoService.existsById(id)) {
            espacioFisicoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}