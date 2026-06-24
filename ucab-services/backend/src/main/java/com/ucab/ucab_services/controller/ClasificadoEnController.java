package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import com.ucab.ucab_services.service.ClasificadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clasificados-en")
public class ClasificadoEnController {

    @Autowired
    private ClasificadoEnService clasificadoEnService;

    @GetMapping
    public List<ClasificadoEn> getAllClasificadosEn() {
        return clasificadoEnService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasificadoEn> getClasificadoEnById(@PathVariable ClasificadoEnId id) {
        Optional<ClasificadoEn> clasificadoEn = clasificadoEnService.findById(id);
        return clasificadoEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClasificadoEn createClasificadoEn(@RequestBody ClasificadoEn clasificadoEn) {
        return clasificadoEnService.save(clasificadoEn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasificadoEn> updateClasificadoEn(@PathVariable ClasificadoEnId id, @RequestBody ClasificadoEn clasificadoEnDetails) {
        Optional<ClasificadoEn> clasificadoEnOptional = clasificadoEnService.findById(id);
        if (clasificadoEnOptional.isPresent()) {
            ClasificadoEn clasificadoEn = clasificadoEnOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(clasificadoEnService.save(clasificadoEn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasificadoEn(@PathVariable ClasificadoEnId id) {
        if (clasificadoEnService.existsById(id)) {
            clasificadoEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}