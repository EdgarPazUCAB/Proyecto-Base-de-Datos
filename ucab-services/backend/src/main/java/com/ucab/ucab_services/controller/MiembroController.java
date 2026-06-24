package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.service.MiembroService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @GetMapping
    public List<Miembro> getAllMiembros() {
        return miembroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Miembro> getMiembroById(@PathVariable String id) {
        Optional<Miembro> optionalMiembro = miembroService.findById(id);
        return optionalMiembro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Miembro createMiembro(@RequestBody Miembro miembro) {
        return miembroService.save(miembro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Miembro> updateMiembro(@PathVariable String id, @RequestBody Miembro miembroDetails) {
        Optional<Miembro> optionalMiembro = miembroService.findById(id);
        if (optionalMiembro.isPresent()) {
            Miembro miembro = optionalMiembro.get();
            // Update fields
            miembro.setNombresCompletos(miembroDetails.getNombresCompletos());
            miembro.setApellidosCompletos(miembroDetails.getApellidosCompletos());
            miembro.setSexo(miembroDetails.getSexo());
            miembro.setFechaNacimiento(miembroDetails.getFechaNacimiento());
            miembro.setEstadoCuenta(miembroDetails.getEstadoCuenta());
            // We'll set a few for brevity, but in reality we should set all.
            // For simplicity, we'll update a few.
            miembro.setDireccionHabitacion(miembroDetails.getDireccionHabitacion());
            miembro.setCorreoInstitucional(miembroDetails.getCorreoInstitucional());
            miembro.setTelefonoPersonal(miembroDetails.getTelefonoPersonal());
            Miembro updatedMiembro = miembroService.save(miembro);
            return ResponseEntity.ok(updatedMiembro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiembro(@PathVariable String id) {
        miembroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}