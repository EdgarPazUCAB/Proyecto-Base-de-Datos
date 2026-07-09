package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AcompananteTemporal;
import com.ucab.ucab_services.repository.AcompananteTemporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @PostMapping
    public ResponseEntity<?> createAcompanante(@RequestBody AcompananteTemporal acompananteTemporal) {
        try {
            if (acompananteTemporal.getEstadoActivo() == null) {
                acompananteTemporal.setEstadoActivo(true);
            }
            
            String identificador = acompananteTemporal.getSolicitudServicio().getIdentificador();
            
            jdbcTemplate.update(
                "INSERT INTO Acompanante_Temporal (Documento_identidad_Acom, Identificador, Nombre_acompanante, Estado_activo) " +
                "VALUES (?, ?, ?, ?)",
                acompananteTemporal.getDocumentoIdentidadAcom(),
                identificador,
                acompananteTemporal.getNombreAcompanante(),
                acompananteTemporal.getEstadoActivo()
            );
            
            return ResponseEntity.ok(acompananteTemporal);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar acompañante: " + e.getMessage());
        }
    }
}
