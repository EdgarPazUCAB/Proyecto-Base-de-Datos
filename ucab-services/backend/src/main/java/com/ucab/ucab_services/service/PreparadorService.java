package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Preparador;
import java.util.List;
import java.util.Optional;

public interface PreparadorService {
    List<Preparador> findAll();
    Optional<Preparador> findById(String id);
    Preparador save(Preparador preparador);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}