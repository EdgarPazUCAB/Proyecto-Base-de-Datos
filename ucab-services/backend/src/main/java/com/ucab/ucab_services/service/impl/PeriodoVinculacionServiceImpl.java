package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import com.ucab.ucab_services.repository.PeriodoVinculacionRepository;
import com.ucab.ucab_services.service.PeriodoVinculacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodoVinculacionServiceImpl implements PeriodoVinculacionService {

    @Autowired
    private PeriodoVinculacionRepository periodoVinculacionRepository;

    @Override
    public List<PeriodoVinculacion> findAll() {
        return periodoVinculacionRepository.findAll();
    }

    @Override
    public Optional<PeriodoVinculacion> findById(PeriodoVinculacionId id) {
        return periodoVinculacionRepository.findById(id);
    }

    @Override
    public PeriodoVinculacion save(PeriodoVinculacion periodoVinculacion) {
        return periodoVinculacionRepository.save(periodoVinculacion);
    }

    @Override
    public void deleteById(PeriodoVinculacionId id) {
        periodoVinculacionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(PeriodoVinculacionId id) {
        return periodoVinculacionRepository.existsById(id);
    }

    @Override
    public long count() {
        return periodoVinculacionRepository.count();
    }
}