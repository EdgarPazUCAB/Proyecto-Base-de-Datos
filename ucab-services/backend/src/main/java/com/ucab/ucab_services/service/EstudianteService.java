package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Estudiante;
import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<Estudiante> findAll();
    Optional<Estudiante> findById(String id);
    Estudiante save(Estudiante estudiante);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}