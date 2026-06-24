package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Egresado;
import com.ucab.ucab_services.repository.EgresadoRepository;
import com.ucab.ucab_services.service.EgresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EgresadoServiceImpl implements EgresadoService {

    @Autowired
    private EgresadoRepository egresadoRepository;

    @Override
    public List<Egresado> findAll() {
        return egresadoRepository.findAll();
    }

    @Override
    public Optional<Egresado> findById(String id) {
        return egresadoRepository.findById(id);
    }

    @Override
    public Egresado save(Egresado egresado) {
        return egresadoRepository.save(egresado);
    }

    @Override
    public void deleteById(String id) {
        egresadoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return egresadoRepository.existsById(id);
    }

    @Override
    public long count() {
        return egresadoRepository.count();
    }
}