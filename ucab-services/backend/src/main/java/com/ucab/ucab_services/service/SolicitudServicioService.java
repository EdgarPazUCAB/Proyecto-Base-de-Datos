package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.SolicitudServicio;
import java.util.List;

public interface SolicitudServicioService {
    SolicitudServicio save(SolicitudServicio solicitudServicio);
    SolicitudServicio findById(String id);
    List<SolicitudServicio> findAll();
    void deleteById(String id);
}