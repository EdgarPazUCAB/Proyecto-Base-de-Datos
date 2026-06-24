package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import com.ucab.ucab_services.service.AuditoriaSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auditoria-sesiones")
public class AuditoriaSesionController {

    @Autowired
    private AuditoriaSesionService auditoriaSesionService;

    @GetMapping
    public List<AuditoriaSesion> getAllAuditoriaSesiones() {
        return auditoriaSesionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaSesion> getAuditoriaSesionById(@PathVariable AuditoriaSesionId id) {
        Optional<AuditoriaSesion> auditoriaSesion = auditoriaSesionService.findById(id);
        return auditoriaSesion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AuditoriaSesion createAuditoriaSesion(@RequestBody AuditoriaSesion auditoriaSesion) {
        return auditoriaSesionService.save(auditoriaSesion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaSesion> updateAuditoriaSesion(@PathVariable AuditoriaSesionId id, @RequestBody AuditoriaSesion auditoriaSesionDetails) {
        Optional<AuditoriaSesion> auditoriaSesionOptional = auditoriaSesionService.findById(id);
        if (auditoriaSesionOptional.isPresent()) {
            AuditoriaSesion auditoriaSesion = auditoriaSesionOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(auditoriaSesionService.save(auditoriaSesion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditoriaSesion(@PathVariable AuditoriaSesionId id) {
        if (auditoriaSesionService.existsById(id)) {
            auditoriaSesionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}