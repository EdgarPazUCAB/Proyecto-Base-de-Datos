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

    @GetMapping("/buscar")
    public ResponseEntity<ClasificadoEn> getClasificadoEnById(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestParam String tipoCategoria) {
        ClasificadoEnId id = new ClasificadoEnId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        id.setTipoCategoria(tipoCategoria);
        Optional<ClasificadoEn> clasificadoEn = clasificadoEnService.findById(id);
        return clasificadoEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClasificadoEn createClasificadoEn(@RequestBody ClasificadoEn clasificadoEn) {

        return clasificadoEnService.save(clasificadoEn);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ClasificadoEn> updateClasificadoEn(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestParam String tipoCategoria,
            @RequestBody ClasificadoEn clasificadoEnDetails) {
        ClasificadoEnId id = new ClasificadoEnId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        id.setTipoCategoria(tipoCategoria);
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

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteClasificadoEn(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestParam String tipoCategoria) {
        ClasificadoEnId id = new ClasificadoEnId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        id.setTipoCategoria(tipoCategoria);
        if (clasificadoEnService.existsById(id)) {
            clasificadoEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}