package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import com.ucab.ucab_services.repository.EntidadPrestadoraRepository;
import com.ucab.ucab_services.service.EntidadPrestadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadPrestadoraServiceImpl implements EntidadPrestadoraService {

    @Autowired
    private EntidadPrestadoraRepository entidadPrestadoraRepository;

    @Override
    public List<EntidadPrestadora> findAll() {
        return entidadPrestadoraRepository.findAll();
    }

    @Override
    public Optional<EntidadPrestadora> findById(Integer id) {
        return entidadPrestadoraRepository.findById(id);
    }

    @Override
    public EntidadPrestadora save(EntidadPrestadora entidadPrestadora) {
        return entidadPrestadoraRepository.save(entidadPrestadora);
    }

    @Override
    public void deleteById(Integer id) {
        entidadPrestadoraRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return entidadPrestadoraRepository.existsById(id);
    }

    @Override
    public long count() {
        return entidadPrestadoraRepository.count();
    }
}