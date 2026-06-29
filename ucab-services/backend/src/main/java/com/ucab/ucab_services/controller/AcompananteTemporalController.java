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

    @PostMapping
    public AcompananteTemporal createAcompanante(@RequestBody AcompananteTemporal acompananteTemporal) {
        // Asignamos por defecto el estado activo a true, como lo indica la BD
        if (acompananteTemporal.getEstadoActivo() == null) {
            acompananteTemporal.setEstadoActivo(true);
        }
        return acompananteTemporalRepository.save(acompananteTemporal);
    }
}
