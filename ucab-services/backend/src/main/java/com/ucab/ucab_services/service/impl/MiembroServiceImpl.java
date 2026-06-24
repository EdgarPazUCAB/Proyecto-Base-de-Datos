package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroServiceImpl implements MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    public Miembro save(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    @Override
    public Optional<Miembro> findById(String id) {
        return miembroRepository.findById(id);
    }

    @Override
    public List<Miembro> findAll() {
        return miembroRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        miembroRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return miembroRepository.existsById(id);
    }

    @Override
    public long count() {
        return miembroRepository.count();
    }
}