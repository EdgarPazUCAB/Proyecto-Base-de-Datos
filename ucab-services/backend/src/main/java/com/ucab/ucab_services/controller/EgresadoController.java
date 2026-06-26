package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Egresado;
import com.ucab.ucab_services.service.EgresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/egresados")
public class EgresadoController {

    @Autowired
    private EgresadoService egresadoService;

    @GetMapping
    public List<Egresado> getAllEgresados() {
        return egresadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Egresado> getEgresadoById(@PathVariable String id) {
        Optional<Egresado> egresado = egresadoService.findById(id);
        return egresado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Egresado createEgresado(@RequestBody Egresado egresado) {
        return egresadoService.save(egresado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Egresado> updateEgresado(@PathVariable String id, @RequestBody Egresado egresadoDetails) {
        Optional<Egresado> egresadoOptional = egresadoService.findById(id);
        if (egresadoOptional.isPresent()) {
            Egresado egresado = egresadoOptional.get();
            // Update fields via miembro composition
            if (egresadoDetails.getMiembro() != null) {
                if (egresado.getMiembro() == null) {
                    egresado.setMiembro(egresadoDetails.getMiembro());
                } else {
                    egresado.getMiembro().setNombresCompletos(egresadoDetails.getMiembro().getNombresCompletos());
                    egresado.getMiembro().setApellidosCompletos(egresadoDetails.getMiembro().getApellidosCompletos());
                    egresado.getMiembro().setFechaNacimiento(egresadoDetails.getMiembro().getFechaNacimiento());
                    egresado.getMiembro().setSexo(egresadoDetails.getMiembro().getSexo());
                    egresado.getMiembro().setDireccionHabitacion(egresadoDetails.getMiembro().getDireccionHabitacion());
                    egresado.getMiembro().setTelefonoPersonal(egresadoDetails.getMiembro().getTelefonoPersonal());
                    egresado.getMiembro().setCorreoInstitucional(egresadoDetails.getMiembro().getCorreoInstitucional());
                    egresado.getMiembro().setFechaApertura(egresadoDetails.getMiembro().getFechaApertura());
                }
            }
            egresado.setTitulo(egresadoDetails.getTitulo());
            egresado.setAnoGraduacion(egresadoDetails.getAnoGraduacion());
            egresado.setIndiceAcademico(egresadoDetails.getIndiceAcademico());
            Egresado updatedEgresado = egresadoService.save(egresado);
            return ResponseEntity.ok(updatedEgresado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEgresado(@PathVariable String id) {
        if (egresadoService.existsById(id)) {
            egresadoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}