package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Docente;
import java.util.List;
import java.util.Optional;

public interface DocenteService {
    List<Docente> findAll();
    Optional<Docente> findById(String id);
    Docente save(Docente docente);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}