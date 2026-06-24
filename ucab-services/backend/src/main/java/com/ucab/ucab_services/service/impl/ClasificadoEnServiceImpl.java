package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import com.ucab.ucab_services.repository.ClasificadoEnRepository;
import com.ucab.ucab_services.service.ClasificadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasificadoEnServiceImpl implements ClasificadoEnService {

    @Autowired
    private ClasificadoEnRepository clasificadoEnRepository;

    @Override
    public List<ClasificadoEn> findAll() {
        return clasificadoEnRepository.findAll();
    }

    @Override
    public Optional<ClasificadoEn> findById(ClasificadoEnId id) {
        return clasificadoEnRepository.findById(id);
    }

    @Override
    public ClasificadoEn save(ClasificadoEn clasificadoEn) {
        return clasificadoEnRepository.save(clasificadoEn);
    }

    @Override
    public void deleteById(ClasificadoEnId id) {
        clasificadoEnRepository.deleteById(id);
    }

    @Override
    public boolean existsById(ClasificadoEnId id) {
        return clasificadoEnRepository.existsById(id);
    }

    @Override
    public long count() {
        return clasificadoEnRepository.count();
    }
}