package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import com.ucab.ucab_services.repository.TarifaServicioRepository;
import com.ucab.ucab_services.service.TarifaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaServicioServiceImpl implements TarifaServicioService {

    @Autowired
    private TarifaServicioRepository tarifaServicioRepository;

    @Override
    public List<TarifaServicio> findAll() {
        return tarifaServicioRepository.findAll();
    }

    @Override
    public Optional<TarifaServicio> findById(TarifaServicioId id) {
        return tarifaServicioRepository.findById(id);
    }

    @Override
    public TarifaServicio save(TarifaServicio tarifaServicio) {
        return tarifaServicioRepository.save(tarifaServicio);
    }

    @Override
    public void deleteById(TarifaServicioId id) {
        tarifaServicioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(TarifaServicioId id) {
        return tarifaServicioRepository.existsById(id);
    }

    @Override
    public long count() {
        return tarifaServicioRepository.count();
    }
}