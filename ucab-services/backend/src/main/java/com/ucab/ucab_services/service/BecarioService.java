package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Becario;
import java.util.List;
import java.util.Optional;

public interface BecarioService {
    List<Becario> findAll();
    Optional<Becario> findById(String id);
    Becario save(Becario becario);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}