package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import java.util.List;
import java.util.Optional;

public interface CategoriaFidelidadService {
    List<CategoriaFidelidad> findAll();
    Optional<CategoriaFidelidad> findById(String id);
    CategoriaFidelidad save(CategoriaFidelidad categoriaFidelidad);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}