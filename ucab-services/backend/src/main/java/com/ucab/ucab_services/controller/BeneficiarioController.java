package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Beneficiario;
import com.ucab.ucab_services.service.BeneficiarioService;
import java.util.ArrayList;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
@CrossOrigin(origins = "http://localhost:4200")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    // <-- CAMBIO APLICADO: Devolver un JSON limpio para evitar el "no session"
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllBeneficiarios() {
        List<Beneficiario> beneficiarios = beneficiarioService.findAll();
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Beneficiario b : beneficiarios) {
            Map<String, Object> map = new HashMap<>();
            map.put("documentoIdentidad", b.getDocumentoIdentidad());
            map.put("nombre", b.getNombre());
            map.put("parentesco", b.getParentesco());
            map.put("estatusBeneficios", b.getEstatusBeneficios());

            // Extraemos solo la cédula del miembro principal para poder filtrar en Angular
            if (b.getMiembro() != null) {
                Map<String, String> miembroMap = new HashMap<>();
                miembroMap.put("cedulaMiembro", b.getMiembro().getCedulaMiembro());
                map.put("miembro", miembroMap);
            }

            responseList.add(map);
        }

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> getBeneficiarioById(@PathVariable String id) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        return optionalBeneficiario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createBeneficiario(@RequestBody Beneficiario beneficiario) {

        // Asignamos fecha de inicio y estatus por defecto
        if(beneficiario.getFechaInicio() == null) {
            beneficiario.setFechaInicio(new java.sql.Date(System.currentTimeMillis()));
        }
        if(beneficiario.getEstatusBeneficios() == null) {
            beneficiario.setEstatusBeneficios("Activo");
        }

        beneficiarioService.save(beneficiario);

        // EVITA EL ERROR DE "no session" AL RESPONDER JSON LIMPIO
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Vínculo familiar registrado con éxito");
        response.put("documento", beneficiario.getDocumentoIdentidad());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> updateBeneficiario(@PathVariable String id, @RequestBody Beneficiario beneficiarioDetails) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        if (optionalBeneficiario.isPresent()) {
            Beneficiario beneficiario = optionalBeneficiario.get();
            beneficiario.setDocumentoIdentidad(beneficiarioDetails.getDocumentoIdentidad());
            beneficiario.setNombre(beneficiarioDetails.getNombre());
            beneficiario.setFechaNacimientoBeneficiario(beneficiarioDetails.getFechaNacimientoBeneficiario());
            beneficiario.setParentesco(beneficiarioDetails.getParentesco());
            beneficiario.setEsquemaVacunacion(beneficiarioDetails.getEsquemaVacunacion());
            beneficiario.setCentroEduInicial(beneficiarioDetails.getCentroEduInicial());
            beneficiario.setConstanciaEstUniversitarios(beneficiarioDetails.getConstanciaEstUniversitarios());
            beneficiario.setCertificadoSolteria(beneficiarioDetails.getCertificadoSolteria());
            beneficiario.setEstatusBeneficios(beneficiarioDetails.getEstatusBeneficios());
            beneficiario.setFechaInicio(beneficiarioDetails.getFechaInicio());
            beneficiario.setFechaFin(beneficiarioDetails.getFechaFin());
            Beneficiario updatedBeneficiario = beneficiarioService.save(beneficiario);
            return ResponseEntity.ok(updatedBeneficiario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiario(@PathVariable String id) {
        beneficiarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}