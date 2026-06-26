package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import com.ucab.ucab_services.service.EspacioFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * SOLO CONSULTA: EspacioFisico representa infraestructura física real
 * que no se crea, edita ni elimina desde la aplicación web. El campo
 * "estatus" (Disponible/Ocupado/Mantenimiento) SÍ es dinámico, pero
 * cambia automáticamente vía los triggers de PostgreSQL al crear o
 * cerrar una Solicitud_Servicio — no a través de un endpoint manual
 * de edición aquí.
 */
@RestController
@RequestMapping("/api/espacios-fisicos")
public class EspacioFisicoController {
    @Autowired
    private EspacioFisicoService espacioFisicoService;

    @GetMapping
    public List<EspacioFisico> getAllEspaciosFisicos() {
        return espacioFisicoService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<EspacioFisico> getEspacioFisicoById(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestParam Integer numIdentificador) {
        EspacioFisicoId id = new EspacioFisicoId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        Optional<EspacioFisico> espacioFisico = espacioFisicoService.findById(id);
        return espacioFisico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}