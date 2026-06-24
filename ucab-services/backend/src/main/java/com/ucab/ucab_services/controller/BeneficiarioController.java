package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Beneficiario;
import com.ucab.ucab_services.service.BeneficiarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @GetMapping
    public List<Beneficiario> getAllBeneficiarios() {
        return beneficiarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> getBeneficiarioById(@PathVariable String id) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        return optionalBeneficiario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Beneficiario createBeneficiario(@RequestBody Beneficiario beneficiario) {
        return beneficiarioService.save(beneficiario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> updateBeneficiario(@PathVariable String id, @RequestBody Beneficiario beneficiarioDetails) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        if (optionalBeneficiario.isPresent()) {
            Beneficiario beneficiario = optionalBeneficiario.get();
            // Update fields - in a real app, you'd map only relevant fields
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