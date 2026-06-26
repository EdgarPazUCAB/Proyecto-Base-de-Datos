package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Docente;
import com.ucab.ucab_services.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public List<Docente> getAllDocentes() {
        return docenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable String id) {
        Optional<Docente> docente = docenteService.findById(id);
        return docente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Docente createDocente(@RequestBody Docente docente) {
        return docenteService.save(docente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> updateDocente(@PathVariable String id, @RequestBody Docente docenteDetails) {
        Optional<Docente> docenteOptional = docenteService.findById(id);
        if (docenteOptional.isPresent()) {
            Docente docente = docenteOptional.get();
            // Update fields via miembro composition
            if (docenteDetails.getMiembro() != null) {
                if (docente.getMiembro() == null) {
                    docente.setMiembro(docenteDetails.getMiembro());
                } else {
                    docente.getMiembro().setNombresCompletos(docenteDetails.getMiembro().getNombresCompletos());
                    docente.getMiembro().setApellidosCompletos(docenteDetails.getMiembro().getApellidosCompletos());
                    docente.getMiembro().setFechaNacimiento(docenteDetails.getMiembro().getFechaNacimiento());
                    docente.getMiembro().setSexo(docenteDetails.getMiembro().getSexo());
                    docente.getMiembro().setDireccionHabitacion(docenteDetails.getMiembro().getDireccionHabitacion());
                    docente.getMiembro().setTelefonoPersonal(docenteDetails.getMiembro().getTelefonoPersonal());
                    docente.getMiembro().setCorreoInstitucional(docenteDetails.getMiembro().getCorreoInstitucional());
                    docente.getMiembro().setFechaApertura(docenteDetails.getMiembro().getFechaApertura());
                }
            }
            docente.setCodigoInvestigador(docenteDetails.getCodigoInvestigador());
            docente.setEscalafonDocente(docenteDetails.getEscalafonDocente());
            docente.setCargaSemanal(docenteDetails.getCargaSemanal());
            Docente updatedDocente = docenteService.save(docente);
            return ResponseEntity.ok(updatedDocente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable String id) {
        if (docenteService.existsById(id)) {
            docenteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}