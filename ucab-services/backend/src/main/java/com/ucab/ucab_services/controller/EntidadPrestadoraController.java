package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import com.ucab.ucab_services.service.EntidadPrestadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entidades-prestadoras")
public class EntidadPrestadoraController {

    @Autowired
    private EntidadPrestadoraService entidadPrestadoraService;

    @GetMapping
    public List<EntidadPrestadora> getAllEntidadesPrestadoras() {
        return entidadPrestadoraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadPrestadora> getEntidadPrestadoraById(@PathVariable Integer id) {
        Optional<EntidadPrestadora> entidadPrestadora = entidadPrestadoraService.findById(id);
        return entidadPrestadora.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadPrestadora createEntidadPrestadora(@RequestBody EntidadPrestadora entidadPrestadora) {
        return entidadPrestadoraService.save(entidadPrestadora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntidadPrestadora> updateEntidadPrestadora(@PathVariable Integer id, @RequestBody EntidadPrestadora entidadPrestadoraDetails) {
        Optional<EntidadPrestadora> entidadPrestadoraOptional = entidadPrestadoraService.findById(id);
        if (entidadPrestadoraOptional.isPresent()) {
            EntidadPrestadora entidadPrestadora = entidadPrestadoraOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(entidadPrestadoraService.save(entidadPrestadora));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntidadPrestadora(@PathVariable Integer id) {
        if (entidadPrestadoraService.existsById(id)) {
            entidadPrestadoraService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}