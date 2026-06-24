package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import com.ucab.ucab_services.service.CategoriaFidelidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias-fidelidad")
public class CategoriaFidelidadController {

    @Autowired
    private CategoriaFidelidadService categoriaFidelidadService;

    @GetMapping
    public List<CategoriaFidelidad> getAllCategoriasFidelidad() {
        return categoriaFidelidadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaFidelidad> getCategoriaFidelidadById(@PathVariable String id) {
        Optional<CategoriaFidelidad> categoriaFidelidad = categoriaFidelidadService.findById(id);
        return categoriaFidelidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoriaFidelidad createCategoriaFidelidad(@RequestBody CategoriaFidelidad categoriaFidelidad) {
        return categoriaFidelidadService.save(categoriaFidelidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaFidelidad> updateCategoriaFidelidad(@PathVariable String id, @RequestBody CategoriaFidelidad categoriaFidelidadDetails) {
        Optional<CategoriaFidelidad> categoriaFidelidadOptional = categoriaFidelidadService.findById(id);
        if (categoriaFidelidadOptional.isPresent()) {
            CategoriaFidelidad categoriaFidelidad = categoriaFidelidadOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            categoriaFidelidad.setTipoCategoria(categoriaFidelidadDetails.getTipoCategoria());
            categoriaFidelidad.setRangoIndiceMin(categoriaFidelidadDetails.getRangoIndiceMin());
            categoriaFidelidad.setRangoIndiceMax(categoriaFidelidadDetails.getRangoIndiceMax());
            categoriaFidelidad.setDescuentoGlobal(categoriaFidelidadDetails.getDescuentoGlobal());
            categoriaFidelidad.setPrioridadReservacion(categoriaFidelidadDetails.getPrioridadReservacion());
            return ResponseEntity.ok(categoriaFidelidadService.save(categoriaFidelidad));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaFidelidad(@PathVariable String id) {
        if (categoriaFidelidadService.existsById(id)) {
            categoriaFidelidadService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}