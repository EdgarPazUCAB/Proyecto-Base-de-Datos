package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import com.ucab.ucab_services.service.TarifaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * SOLO CONSULTA: Tarifa_Servicio es un dato institucional (precios,
 * límites de costo por perfil/sede) que la UCAB gestiona directamente
 * en la base de datos — no se crea, edita ni elimina desde la app web.
 */
@RestController
@RequestMapping("/api/tarifas-servicio")
public class TarifaServicioController {
    @Autowired
    private TarifaServicioService tarifaServicioService;

    @GetMapping
    public List<TarifaServicio> getAllTarifaServicios() {
        return tarifaServicioService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<TarifaServicio> getTarifaServicioById(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio) {
        TarifaServicioId id = new TarifaServicioId();

        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        Optional<TarifaServicio> tarifaServicio = tarifaServicioService.findById(id);
        return tarifaServicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}