package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import java.util.List;
import java.util.Optional;

public interface AplicaEnService {
    List<AplicaEn> findAll();
    Optional<AplicaEn> findById(AplicaEnId id);
    AplicaEn save(AplicaEn aplicaEn);
    void deleteById(AplicaEnId id);
    boolean existsById(AplicaEnId id);
    long count();
}