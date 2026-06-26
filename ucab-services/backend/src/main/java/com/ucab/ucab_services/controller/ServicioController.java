package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Servicio;
import com.ucab.ucab_services.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SOLO CONSULTA: Servicio es un dato institucional que la UCAB
 * gestiona directamente en la base de datos (qué servicios existen,
 * quién los presta) — no se crea, edita ni elimina desde la app web.
 */
@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable String id) {
        Servicio servicio = servicioService.findById(id);
        return servicio != null ? ResponseEntity.ok(servicio) : ResponseEntity.notFound().build();
    }
}