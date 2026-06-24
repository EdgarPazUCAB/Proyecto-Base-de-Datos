package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Estudiante;
import com.ucab.ucab_services.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable String id) {
        Optional<Estudiante> estudiante = estudianteService.findById(id);
        return estudiante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.save(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable String id, @RequestBody Estudiante estudianteDetails) {
        Optional<Estudiante> estudianteOptional = estudianteService.findById(id);
        if (estudianteOptional.isPresent()) {
            Estudiante estudiante = estudianteOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            estudiante.setNombresCompletos(estudianteDetails.getNombresCompletos());
            estudiante.setApellidosCompletos(estudianteDetails.getApellidosCompletos());
            estudiante.setFechaNacimiento(estudianteDetails.getFechaNacimiento());
            estudiante.setSexo(estudianteDetails.getSexo());
            estudiante.setDireccionHabitacion(estudianteDetails.getDireccionHabitacion());
            estudiante.setTelefonoPersonal(estudianteDetails.getTelefonoPersonal());
            estudiante.setCorreoInstitucional(estudianteDetails.getCorreoInstitucional());
            estudiante.setFechaApertura(estudianteDetails.getFechaApertura());
            Estudiante updatedEstudiante = estudianteService.save(estudiante);
            return ResponseEntity.ok(updatedEstudiante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable String id) {
        if (estudianteService.existsById(id)) {
            estudianteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}