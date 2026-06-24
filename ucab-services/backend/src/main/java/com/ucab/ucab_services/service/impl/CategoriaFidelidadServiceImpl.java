package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import com.ucab.ucab_services.repository.CategoriaFidelidadRepository;
import com.ucab.ucab_services.service.CategoriaFidelidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaFidelidadServiceImpl implements CategoriaFidelidadService {

    @Autowired
    private CategoriaFidelidadRepository categoriaFidelidadRepository;

    @Override
    public List<CategoriaFidelidad> findAll() {
        return categoriaFidelidadRepository.findAll();
    }

    @Override
    public Optional<CategoriaFidelidad> findById(String id) {
        return categoriaFidelidadRepository.findById(id);
    }

    @Override
    public CategoriaFidelidad save(CategoriaFidelidad categoriaFidelidad) {
        return categoriaFidelidadRepository.save(categoriaFidelidad);
    }

    @Override
    public void deleteById(String id) {
        categoriaFidelidadRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return categoriaFidelidadRepository.existsById(id);
    }

    @Override
    public long count() {
        return categoriaFidelidadRepository.count();
    }
}