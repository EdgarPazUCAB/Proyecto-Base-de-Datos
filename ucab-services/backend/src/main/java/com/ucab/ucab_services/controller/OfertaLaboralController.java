package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.service.OfertaLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "http://localhost:4200")
public class OfertaLaboralController {

    @Autowired
    private OfertaLaboralService ofertaService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerTodasLasOfertas() {
        return ResponseEntity.ok(ofertaService.obtenerTodasLasOfertas());
    }

    @GetMapping("/detalle")
    public ResponseEntity<Map<String, Object>> obtenerOfertaDetalle(
            @RequestParam("entidad") int idEntidad, 
            @RequestParam("cargo") String cargo) {
        Map<String, Object> detalle = ofertaService.obtenerOfertaDetalle(idEntidad, cargo);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }
}
