package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Preparador;
import com.ucab.ucab_services.repository.PreparadorRepository;
import com.ucab.ucab_services.service.PreparadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreparadorServiceImpl implements PreparadorService {

    @Autowired
    private PreparadorRepository preparadorRepository;

    @Override
    public List<Preparador> findAll() {
        return preparadorRepository.findAll();
    }

    @Override
    public Optional<Preparador> findById(String id) {
        return preparadorRepository.findById(id);
    }

    @Override
    public Preparador save(Preparador preparador) {
        return preparadorRepository.save(preparador);
    }

    @Override
    public void deleteById(String id) {
        preparadorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return preparadorRepository.existsById(id);
    }

    @Override
    public long count() {
        return preparadorRepository.count();
    }
}