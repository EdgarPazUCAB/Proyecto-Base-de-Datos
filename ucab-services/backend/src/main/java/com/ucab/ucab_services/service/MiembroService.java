package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import java.util.List;
import java.util.Optional;

public interface MiembroService {
    Miembro save(Miembro miembro);
    Optional<Miembro> findById(String id);
    List<Miembro> findAll();
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}