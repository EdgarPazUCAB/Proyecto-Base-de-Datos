package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import java.util.List;
import java.util.Optional;

public interface EntidadPrestadoraService {
    List<EntidadPrestadora> findAll();
    Optional<EntidadPrestadora> findById(Integer id);
    EntidadPrestadora save(EntidadPrestadora entidadPrestadora);
    void deleteById(Integer id);
    boolean existsById(Integer id);
    long count();
}