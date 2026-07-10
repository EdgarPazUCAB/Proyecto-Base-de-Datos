package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AcompananteTemporal;
import com.ucab.ucab_services.repository.AcompananteTemporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/acompanantes-temporales")
@CrossOrigin(origins = "http://localhost:4200")
public class AcompananteTemporalController {

    @Autowired
    private AcompananteTemporalRepository acompananteTemporalRepository;

    @GetMapping
    public List<AcompananteTemporal> getAllAcompanantes() {
        return acompananteTemporalRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createAcompanante(@RequestBody AcompananteTemporal acompananteTemporal) {
        try {
            // 1. Validar proactivamente si el documento ya existe
            if (acompananteTemporalRepository.existsById(acompananteTemporal.getDocumentoIdentidadAcom())) {
                // Devolvemos código 400 (Bad Request) con un JSON que Angular leerá en err.error.message
                return ResponseEntity.badRequest().body(Map.of("message", "El acompañante con este documento ya se encuentra registrado."));
            }

            if (acompananteTemporal.getEstadoActivo() == null) {
                acompananteTemporal.setEstadoActivo(true);
            }
            
            // 2. Usar JPA para guardar en lugar de JdbcTemplate manual
            AcompananteTemporal guardado = acompananteTemporalRepository.save(acompananteTemporal);
            
            return ResponseEntity.ok(guardado);

        } catch (Exception e) {
            // Devolver error estructurado en JSON
            return ResponseEntity.internalServerError().body(Map.of("message", "Error interno al guardar acompañante: " + e.getMessage()));
        }
    }
}