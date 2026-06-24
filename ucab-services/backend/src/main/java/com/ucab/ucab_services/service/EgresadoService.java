package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Egresado;
import java.util.List;
import java.util.Optional;

public interface EgresadoService {
    List<Egresado> findAll();
    Optional<Egresado> findById(String id);
    Egresado save(Egresado egresado);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}