package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Sede;
import com.ucab.ucab_services.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @GetMapping
    public List<Sede> getAllSedes() {
        return sedeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> getSedeById(@PathVariable String id) {
        Sede sede = sedeService.findById(id);
        return sede != null ? ResponseEntity.ok(sede) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Sede createSede(@RequestBody Sede sede) {
        return sedeService.save(sede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sede> updateSede(@PathVariable String id, @RequestBody Sede sedeDetails) {
        Sede sede = sedeService.findById(id);
        if (sede == null) {
            return ResponseEntity.notFound().build();
        }
        sede.setNombreSede(sedeDetails.getNombreSede());
        Sede updatedSede = sedeService.save(sede);
        return ResponseEntity.ok(updatedSede);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable String id) {
        sedeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}