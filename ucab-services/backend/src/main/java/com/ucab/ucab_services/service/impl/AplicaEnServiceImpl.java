package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import com.ucab.ucab_services.repository.AplicaEnRepository;
import com.ucab.ucab_services.service.AplicaEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AplicaEnServiceImpl implements AplicaEnService {

    @Autowired
    private AplicaEnRepository aplicaEnRepository;

    @Override
    public List<AplicaEn> findAll() {
        return aplicaEnRepository.findAll();
    }

    @Override
    public Optional<AplicaEn> findById(AplicaEnId id) {
        return aplicaEnRepository.findById(id);
    }

    @Override
    public AplicaEn save(AplicaEn aplicaEn) {
        return aplicaEnRepository.save(aplicaEn);
    }

    @Override
    public void deleteById(AplicaEnId id) {
        aplicaEnRepository.deleteById(id);
    }

    @Override
    public boolean existsById(AplicaEnId id) {
        return aplicaEnRepository.existsById(id);
    }

    @Override
    public long count() {
        return aplicaEnRepository.count();
    }
}