package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import java.util.List;
import java.util.Optional;

public interface ClasificadoEnService {
    List<ClasificadoEn> findAll();
    Optional<ClasificadoEn> findById(ClasificadoEnId id);
    ClasificadoEn save(ClasificadoEn clasificadoEn);
    void deleteById(ClasificadoEnId id);
    boolean existsById(ClasificadoEnId id);
    long count();
}