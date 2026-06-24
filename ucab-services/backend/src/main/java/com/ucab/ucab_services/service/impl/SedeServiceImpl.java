package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Sede;
import com.ucab.ucab_services.repository.SedeRepository;
import com.ucab.ucab_services.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public Sede save(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Override
    public Sede findById(String id) {
        return sedeRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Sede> findAll() {
        return sedeRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        sedeRepository.deleteById(id);
    }
}