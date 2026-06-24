package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import com.ucab.ucab_services.service.AplicaEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aplica-en")
public class AplicaEnController {

    @Autowired
    private AplicaEnService aplicaEnService;

    @GetMapping
    public List<AplicaEn> getAllAplicaEn() {
        return aplicaEnService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AplicaEn> getAplicaEnById(@PathVariable AplicaEnId id) {
        Optional<AplicaEn> aplicaEn = aplicaEnService.findById(id);
        return aplicaEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AplicaEn createAplicaEn(@RequestBody AplicaEn aplicaEn) {
        return aplicaEnService.save(aplicaEn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AplicaEn> updateAplicaEn(@PathVariable AplicaEnId id, @RequestBody AplicaEn aplicaEnDetails) {
        Optional<AplicaEn> aplicaEnOptional = aplicaEnService.findById(id);
        if (aplicaEnOptional.isPresent()) {
            AplicaEn aplicaEn = aplicaEnOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(aplicaEnService.save(aplicaEn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAplicaEn(@PathVariable AplicaEnId id) {
        if (aplicaEnService.existsById(id)) {
            aplicaEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}