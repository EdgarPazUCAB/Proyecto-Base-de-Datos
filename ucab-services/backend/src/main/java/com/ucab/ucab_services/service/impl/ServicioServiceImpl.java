package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Servicio;
import com.ucab.ucab_services.repository.ServicioRepository;
import com.ucab.ucab_services.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio findById(String id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        servicioRepository.deleteById(id);
    }
}