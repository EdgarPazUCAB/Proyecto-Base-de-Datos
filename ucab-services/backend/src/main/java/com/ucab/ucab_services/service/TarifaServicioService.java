package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import java.util.List;
import java.util.Optional;

public interface TarifaServicioService {
    List<TarifaServicio> findAll();
    Optional<TarifaServicio> findById(TarifaServicioId id);
    TarifaServicio save(TarifaServicio tarifaServicio);
    void deleteById(TarifaServicioId id);
    boolean existsById(TarifaServicioId id);
    long count();
}