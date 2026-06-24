package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import com.ucab.ucab_services.service.MFACodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mfa-codigos")
public class MFACodigoController {
    @Autowired
    private MFACodigoService mfaCodigoService;

    @GetMapping
    public List<MFACodigo> getAllMFA_Codigos() {
        return mfaCodigoService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<MFACodigo> getMFACodigoById(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaGenerado) {
        MFACodigoId id = new MFACodigoId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaGenerado(fechaGenerado);
        Optional<MFACodigo> mfaCodigo = mfaCodigoService.findById(id);
        return mfaCodigo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MFACodigo createMFACodigo(@RequestBody MFACodigo mfaCodigo) {
        return mfaCodigoService.save(mfaCodigo);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<MFACodigo> updateMFACodigo(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaGenerado, @RequestBody MFACodigo mfaCodigoDetails) {
        MFACodigoId id = new MFACodigoId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaGenerado(fechaGenerado);
        Optional<MFACodigo> mfaCodigoOptional = mfaCodigoService.findById(id);
        if (mfaCodigoOptional.isPresent()) {
            MFACodigo mfaCodigo = mfaCodigoOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(mfaCodigoService.save(mfaCodigo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteMFACodigo(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaGenerado) {
        MFACodigoId id = new MFACodigoId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaGenerado(fechaGenerado);
        if (mfaCodigoService.existsById(id)) {
            mfaCodigoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}