package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import java.util.List;
import java.util.Optional;

public interface PeriodoVinculacionService {
    List<PeriodoVinculacion> findAll();
    Optional<PeriodoVinculacion> findById(PeriodoVinculacionId id);
    PeriodoVinculacion save(PeriodoVinculacion periodoVinculacion);
    void deleteById(PeriodoVinculacionId id);
    boolean existsById(PeriodoVinculacionId id);
    long count();
}