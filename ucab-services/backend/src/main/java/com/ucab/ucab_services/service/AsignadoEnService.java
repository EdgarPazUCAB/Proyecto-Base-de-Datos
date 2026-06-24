package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import java.util.List;
import java.util.Optional;

public interface AsignadoEnService {
    List<AsignadoEn> findAll();
    Optional<AsignadoEn> findById(AsignadoEnId id);
    AsignadoEn save(AsignadoEn asignadoEn);
    void deleteById(AsignadoEnId id);
    boolean existsById(AsignadoEnId id);
    long count();
}