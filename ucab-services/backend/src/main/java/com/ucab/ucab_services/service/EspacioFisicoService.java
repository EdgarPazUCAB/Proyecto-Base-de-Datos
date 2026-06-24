package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import java.util.List;
import java.util.Optional;

public interface EspacioFisicoService {
    List<EspacioFisico> findAll();
    Optional<EspacioFisico> findById(EspacioFisicoId id);
    EspacioFisico save(EspacioFisico espacioFisico);
    void deleteById(EspacioFisicoId id);
    boolean existsById(EspacioFisicoId id);
    long count();
}