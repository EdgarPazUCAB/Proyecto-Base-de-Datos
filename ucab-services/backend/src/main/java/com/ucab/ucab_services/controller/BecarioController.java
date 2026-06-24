package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Becario;
import com.ucab.ucab_services.service.BecarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/becarios")
public class BecarioController {

    @Autowired
    private BecarioService becarioService;

    @GetMapping
    public List<Becario> getAllBecarios() {
        return becarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Becario> getBecarioById(@PathVariable String id) {
        Optional<Becario> becario = becarioService.findById(id);
        return becario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Becario createBecario(@RequestBody Becario becario) {
        return becarioService.save(becario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Becario> updateBecario(@PathVariable String id, @RequestBody Becario becarioDetails) {
        Optional<Becario> becarioOptional = becarioService.findById(id);
        if (becarioOptional.isPresent()) {
            Becario becario = becarioOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(becarioService.save(becario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBecario(@PathVariable String id) {
        if (becarioService.existsById(id)) {
            becarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}