package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Beneficiario;
import java.util.List;
import java.util.Optional;

public interface BeneficiarioService {
    Beneficiario save(Beneficiario beneficiario);
    Optional<Beneficiario> findById(String id);
    List<Beneficiario> findAll();
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}