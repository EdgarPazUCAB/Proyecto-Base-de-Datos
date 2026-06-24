package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import com.ucab.ucab_services.service.AsignadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignado-en")
public class AsignadoEnController {
    @Autowired
    private AsignadoEnService asignadoEnService;

    @GetMapping
    public List<AsignadoEn> getAllAsignadoEn() {
        return asignadoEnService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<AsignadoEn> getAsignadoEnById(@RequestParam String codigoServicio,
            @RequestParam String nombreEdif, @RequestParam String direccionInterna,
            @RequestParam Integer numIdentificador) {
        AsignadoEnId id = new AsignadoEnId();
        id.setCodigoServicio(codigoServicio);
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        Optional<AsignadoEn> asignadoEn = asignadoEnService.findById(id);
        return asignadoEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AsignadoEn createAsignadoEn(@RequestBody AsignadoEn asignadoEn) {
        return asignadoEnService.save(asignadoEn);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<AsignadoEn> updateAsignadoEn(@RequestParam String codigoServicio,
            @RequestParam String nombreEdif, @RequestParam String direccionInterna,
            @RequestParam Integer numIdentificador, @RequestBody AsignadoEn asignadoEnDetails) {
        AsignadoEnId id = new AsignadoEnId();
        id.setCodigoServicio(codigoServicio);
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        Optional<AsignadoEn> asignadoEnOptional = asignadoEnService.findById(id);
        if (asignadoEnOptional.isPresent()) {
            AsignadoEn asignadoEn = asignadoEnOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated

            return ResponseEntity.ok(asignadoEnService.save(asignadoEn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteAsignadoEn(@RequestParam String codigoServicio, @RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestParam Integer numIdentificador) {
        AsignadoEnId id = new AsignadoEnId();
        id.setCodigoServicio(codigoServicio);
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        if (asignadoEnService.existsById(id)) {
            asignadoEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}