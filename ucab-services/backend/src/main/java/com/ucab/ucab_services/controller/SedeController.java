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

    // SOLO LECTURA PERMITIDA (GET)
    @GetMapping
    public List<Sede> getAllSedes() {
        return sedeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> getSedeById(@PathVariable String id) {
        // ✅ Se recibe Sede directo y se evalúa si es null
        Sede sede = sedeService.findById(id);
        return sede != null ? ResponseEntity.ok(sede) : ResponseEntity.notFound().build();
    }
}