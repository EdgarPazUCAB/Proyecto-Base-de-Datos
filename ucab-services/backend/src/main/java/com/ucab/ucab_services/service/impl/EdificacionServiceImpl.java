package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import com.ucab.ucab_services.repository.EdificacionRepository;
import com.ucab.ucab_services.service.EdificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdificacionServiceImpl implements EdificacionService {

    @Autowired
    private EdificacionRepository edificacionRepository;

    @Override
    public List<Edificacion> findAll() {
        return edificacionRepository.findAll();
    }

    @Override
    public Optional<Edificacion> findById(EdificacionId id) {
        return edificacionRepository.findById(id);
    }

    @Override
    public Edificacion save(Edificacion edificacion) {
        return edificacionRepository.save(edificacion);
    }

    @Override
    public void deleteById(EdificacionId id) {
        edificacionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(EdificacionId id) {
        return edificacionRepository.existsById(id);
    }

    @Override
    public long count() {
        return edificacionRepository.count();
    }
}