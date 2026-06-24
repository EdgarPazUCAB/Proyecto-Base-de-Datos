package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import com.ucab.ucab_services.repository.AsignadoEnRepository;
import com.ucab.ucab_services.service.AsignadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignadoEnServiceImpl implements AsignadoEnService {

    @Autowired
    private AsignadoEnRepository asignadoEnRepository;

    @Override
    public List<AsignadoEn> findAll() {
        return asignadoEnRepository.findAll();
    }

    @Override
    public Optional<AsignadoEn> findById(AsignadoEnId id) {
        return asignadoEnRepository.findById(id);
    }

    @Override
    public AsignadoEn save(AsignadoEn asignadoEn) {
        return asignadoEnRepository.save(asignadoEn);
    }

    @Override
    public void deleteById(AsignadoEnId id) {
        asignadoEnRepository.deleteById(id);
    }

    @Override
    public boolean existsById(AsignadoEnId id) {
        return asignadoEnRepository.existsById(id);
    }

    @Override
    public long count() {
        return asignadoEnRepository.count();
    }
}