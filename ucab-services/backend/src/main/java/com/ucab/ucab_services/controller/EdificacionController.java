package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import com.ucab.ucab_services.service.EdificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * SOLO CONSULTA: Edificacion representa infraestructura física real
 * que no se crea, edita ni elimina desde la aplicación web — solo
 * cambia por eventos físicos (construcción, demolición, remodelación)
 * gestionados directamente en la base de datos.
 */
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
}