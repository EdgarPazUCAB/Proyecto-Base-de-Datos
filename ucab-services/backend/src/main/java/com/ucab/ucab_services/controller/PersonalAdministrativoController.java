package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import com.ucab.ucab_services.service.PersonalAdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-administrativo")
public class PersonalAdministrativoController {

    @Autowired
    private PersonalAdministrativoService personalAdministrativoService;

    @GetMapping
    public List<PersonalAdministrativo> getAllPersonalAdministrativo() {
        return personalAdministrativoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalAdministrativo> getPersonalAdministrativoById(@PathVariable String id) {
        Optional<PersonalAdministrativo> personalAdministrativo = personalAdministrativoService.findById(id);
        return personalAdministrativo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PersonalAdministrativo createPersonalAdministrativo(@RequestBody PersonalAdministrativo personalAdministrativo) {
        return personalAdministrativoService.save(personalAdministrativo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalAdministrativo> updatePersonalAdministrativo(@PathVariable String id, @RequestBody PersonalAdministrativo personalAdministrativoDetails) {
        Optional<PersonalAdministrativo> personalAdministrativoOptional = personalAdministrativoService.findById(id);
        if (personalAdministrativoOptional.isPresent()) {
            PersonalAdministrativo personalAdministrativo = personalAdministrativoOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(personalAdministrativoService.save(personalAdministrativo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalAdministrativo(@PathVariable String id) {
        if (personalAdministrativoService.existsById(id)) {
            personalAdministrativoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}