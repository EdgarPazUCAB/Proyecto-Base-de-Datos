package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import com.ucab.ucab_services.service.EdificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/edificaciones")
public class EdificacionController {
    @Autowired
    private EdificacionService edificacionService;

    @GetMapping
    public List<Edificacion> getAllEdificaciones() {
        return edificacionService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Edificacion> getEdificacionById(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna) {
        EdificacionId id = new EdificacionId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        Optional<Edificacion> edificacion = edificacionService.findById(id);
        return edificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Edificacion createEdificacion(@RequestBody Edificacion edificacion) {
        return edificacionService.save(edificacion);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Edificacion> updateEdificacion(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestBody Edificacion edificacionDetails) {
        EdificacionId id = new EdificacionId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        Optional<Edificacion> edificacionOptional = edificacionService.findById(id);
        if (edificacionOptional.isPresent()) {
            Edificacion edificacion = edificacionOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(edificacionService.save(edificacion));
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteEdificacion(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna) {
        EdificacionId id = new EdificacionId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        if (edificacionService.existsById(id)) {
            edificacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}