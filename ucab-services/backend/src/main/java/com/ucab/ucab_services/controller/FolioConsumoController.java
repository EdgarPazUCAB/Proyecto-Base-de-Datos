package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.FolioConsumo;
import com.ucab.ucab_services.repository.FolioConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/folios-consumo")
@CrossOrigin(origins = "http://localhost:4200")
public class FolioConsumoController {

    @Autowired
    private FolioConsumoRepository folioConsumoRepository;

    @GetMapping
    public List<Map<String, Object>> getAllFolios() {
        List<FolioConsumo> folios = folioConsumoRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();
        for (FolioConsumo folio : folios) {
            response.add(mapFolio(folio));
        }
        return response;
    }

    @GetMapping("/{identificador}")
    public ResponseEntity<List<Map<String, Object>>> getFolioByIdentificador(@PathVariable String identificador) {
        List<FolioConsumo> folios = folioConsumoRepository.findByIdentificador(identificador);
        if (folios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Map<String, Object>> response = new ArrayList<>();
        for (FolioConsumo folio : folios) {
            response.add(mapFolio(folio));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{cedula}")
    public ResponseEntity<List<Map<String, Object>>> getFoliosByUsuario(@PathVariable String cedula) {
        List<FolioConsumo> folios = folioConsumoRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();
        for (FolioConsumo folio : folios) {
            if (folio.getSolicitudServicio() != null && 
                folio.getSolicitudServicio().getMiembro() != null &&
                cedula.equals(folio.getSolicitudServicio().getMiembro().getCedulaMiembro())) {
                response.add(mapFolio(folio));
            }
        }
        return ResponseEntity.ok(response);
    }

    private Map<String, Object> mapFolio(FolioConsumo folio) {
        Map<String, Object> map = new HashMap<>();
        map.put("identificador", folio.getIdentificador());
        map.put("fechaApertura", folio.getFechaApertura());
        map.put("estadoFolio", folio.getEstadoCierre()); // Frontend uses estadoFolio
        return map;
    }
}