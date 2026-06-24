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

    @GetMapping("/buscar")
    public ResponseEntity<AplicaEn> getAplicaEnById(@RequestParam String nombreSede,
            @RequestParam String codigoServicio, @RequestParam String perfilSolicitante) {
        AplicaEnId id = new AplicaEnId();
        id.setNombreSede(nombreSede);
        id.setCodigoServicio(codigoServicio);
        id.setPerfilSolicitante(perfilSolicitante);
        Optional<AplicaEn> aplicaEn = aplicaEnService.findById(id);
        return aplicaEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AplicaEn createAplicaEn(@RequestBody AplicaEn aplicaEn) {
        return aplicaEnService.save(aplicaEn);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<AplicaEn> updateAplicaEn(@RequestParam String nombreSede, @RequestParam String codigoServicio,
            @RequestParam String perfilSolicitante, @RequestBody AplicaEn aplicaEnDetails) {
        AplicaEnId id = new AplicaEnId();
        id.setNombreSede(nombreSede);
        id.setCodigoServicio(codigoServicio);
        id.setPerfilSolicitante(perfilSolicitante);
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

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteAplicaEn(@RequestParam String nombreSede, @RequestParam String codigoServicio,
            @RequestParam String perfilSolicitante) {
        AplicaEnId id = new AplicaEnId();
        id.setNombreSede(nombreSede);
        id.setCodigoServicio(codigoServicio);
        id.setPerfilSolicitante(perfilSolicitante);
        if (aplicaEnService.existsById(id)) {
            aplicaEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}