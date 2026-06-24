package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import java.util.List;
import java.util.Optional;

public interface EdificacionService {
    List<Edificacion> findAll();
    Optional<Edificacion> findById(EdificacionId id);
    Edificacion save(Edificacion edificacion);
    void deleteById(EdificacionId id);
    boolean existsById(EdificacionId id);
    long count();
}