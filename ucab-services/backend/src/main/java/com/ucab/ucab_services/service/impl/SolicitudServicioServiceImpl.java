package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.SolicitudServicio;
import com.ucab.ucab_services.repository.SolicitudServicioRepository;
import com.ucab.ucab_services.service.SolicitudServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServicioServiceImpl implements SolicitudServicioService {

    @Autowired
    private SolicitudServicioRepository solicitudServicioRepository;

    @Override
    public SolicitudServicio save(SolicitudServicio solicitudServicio) {
        return solicitudServicioRepository.save(solicitudServicio);
    }

    @Override
    public SolicitudServicio findById(String id) {
        return solicitudServicioRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<SolicitudServicio> findAll() {
        return solicitudServicioRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        solicitudServicioRepository.deleteById(id);
    }
}