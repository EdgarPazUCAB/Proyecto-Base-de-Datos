package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Preparador;
import com.ucab.ucab_services.service.PreparadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preparadores")
public class PreparadorController {

    @Autowired
    private PreparadorService preparadorService;

    @GetMapping
    public List<Preparador> getAllPreparadores() {
        return preparadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preparador> getPreparadorById(@PathVariable String id) {
        Optional<Preparador> preparador = preparadorService.findById(id);
        return preparador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Preparador createPreparador(@RequestBody Preparador preparador) {
        return preparadorService.save(preparador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preparador> updatePreparador(@PathVariable String id, @RequestBody Preparador preparadorDetails) {
        Optional<Preparador> preparadorOptional = preparadorService.findById(id);
        if (preparadorOptional.isPresent()) {
            Preparador preparador = preparadorOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(preparadorService.save(preparador));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreparador(@PathVariable String id) {
        if (preparadorService.existsById(id)) {
            preparadorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}