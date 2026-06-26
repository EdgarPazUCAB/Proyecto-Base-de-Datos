package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import com.ucab.ucab_services.service.EntidadPrestadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * SOLO CONSULTA: Entidad_Prestadora (Interna/Externa) es un dato
 * institucional gestionado directamente en la base de datos — no se
 * crea, edita ni elimina desde la app web.
 */
@RestController
@RequestMapping("/api/entidades-prestadoras")
public class EntidadPrestadoraController {

    @Autowired
    private EntidadPrestadoraService entidadPrestadoraService;

    @GetMapping
    public List<EntidadPrestadora> getAllEntidadesPrestadoras() {
        return entidadPrestadoraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadPrestadora> getEntidadPrestadoraById(@PathVariable Integer id) {
        Optional<EntidadPrestadora> entidadPrestadora = entidadPrestadoraService.findById(id);
        return entidadPrestadora.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}