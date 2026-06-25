package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroServiceImpl implements MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Miembro> findAll() {
        return miembroRepository.findAll();
    }

    @Override
    public Optional<Miembro> findById(String id) {
        return miembroRepository.findById(id);
    }

    @Override
    public Miembro save(Miembro miembro) {
        if (miembroRepository.existsById(miembro.getCedulaMiembro()) && miembro.getFechaApertura() == null) {
            throw new RuntimeException("El miembro con cédula " + miembro.getCedulaMiembro() + " ya existe.");
        }

        if (miembro.getClaveHash() != null && !miembro.getClaveHash().startsWith("$2a$")) {
            String hash = passwordEncoder.encode(miembro.getClaveHash());
            miembro.setClaveHash(hash);
        }

        if (miembro.getEstadoCuenta() == null) {
            miembro.setEstadoCuenta("ACTIVO");
        }
        if (miembro.getIntentosFallidos() == null) {
            miembro.setIntentosFallidos(0);
        }

        return miembroRepository.save(miembro);
    }

    @Override
    public void deleteById(String id) {
        miembroRepository.deleteById(id);
    }

    // ✅ MÉTODOS OBLIGATORIOS AÑADIDOS
    @Override
    public boolean existsById(String id) {
        return miembroRepository.existsById(id);
    }

    @Override
    public long count() {
        return miembroRepository.count();
    }
}