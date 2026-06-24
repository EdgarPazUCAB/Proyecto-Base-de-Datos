package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Servicio;
import java.util.List;

public interface ServicioService {
    Servicio save(Servicio servicio);
    Servicio findById(String id);
    List<Servicio> findAll();
    void deleteById(String id);
}